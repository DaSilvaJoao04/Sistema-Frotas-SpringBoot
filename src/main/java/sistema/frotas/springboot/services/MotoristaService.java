package sistema.frotas.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.motorista.MotoristaRequest;
import sistema.frotas.springboot.dto.motorista.MotoristaResponse;
import sistema.frotas.springboot.entities.Motorista;
import sistema.frotas.springboot.enums.CategoriaCNH;
import sistema.frotas.springboot.enums.StatusMotorista;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.MotoristaMapper;
import sistema.frotas.springboot.repositories.MotoristaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final MotoristaMapper motoristaMapper;


    public List<MotoristaResponse> buscarTodosMotoristas (){

        return motoristaRepository.findAll().stream()
                .map(motoristaMapper::toResponse)
                .toList();

    }

    public MotoristaResponse buscarMotoristaPorId(Long id){

        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Motorista não encontrado com ID:" + id));

        return motoristaMapper.toResponse(motorista);

    }


    public MotoristaResponse criarMotorista(MotoristaRequest request){

        if (motoristaRepository.existsByCPF(request.cpf())){
            throw new IllegalArgumentException("Erro: Esse CPF já está vinculado há um motorista");
        }

        if (motoristaRepository.existsByCNH(request.numeroCNH())){
            throw new IllegalArgumentException("Erro: Essa CNH já está vinculada há um motorista");
        }

        Motorista motorista = motoristaMapper.toEntity(request);

        Motorista motoristaSalvo = motoristaRepository.save(motorista);

        return motoristaMapper.toResponse(motoristaSalvo);
    }


    public MotoristaResponse atualizarMotorista(Long id, MotoristaRequest request){

        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Motorista não encontrado com ID: " + id));

        if (motorista.getStatus().equals(StatusMotorista.INATIVO)){
            throw new IllegalStateException("Erro: Motorista está inativo");

        }

        motorista.setNomeCompleto(request.nomeCompleto());
        motorista.setCategoria(request.categoria());
        motorista.setDataValidadeCNH(request.dataValidadeCNH());
        motorista.setTelefone(request.telefone());

        Motorista motoristaAtualizado = motoristaRepository.save(motorista);

        return motoristaMapper.toResponse(motoristaAtualizado);

    }

    public void inativarMotorista(Long id ){

        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Motorista não encontrado com ID:" + id));

        if (motorista.getStatus().equals(StatusMotorista.INATIVO)){
            throw new IllegalStateException("Erro: Motorista já está inativo");

        }

        motorista.setStatus(StatusMotorista.INATIVO);

        motoristaRepository.save(motorista);

    }

    public void afastarMotorista(Long id){

        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Motorista não encontrado com ID: " + id));

        if (motorista.getStatus().equals(StatusMotorista.AFASTADO)){
            throw new IllegalStateException("Erro: Motorista já está afastado");

        }

        if (motorista.getStatus().equals(StatusMotorista.INATIVO)){
            throw new IllegalStateException("Erro: Motorista está inativo");

        }

        motorista.setStatus(StatusMotorista.AFASTADO);

        motoristaRepository.save(motorista);


    }


    public List<MotoristaResponse> buscarMotoristaPorStatus (StatusMotorista statusMotorista){

        return motoristaRepository.findMotoristaByStatus(statusMotorista).stream()
                .map(motoristaMapper::toResponse)
                .toList();

    }

    public List<MotoristaResponse> buscarMotoristaPorCategoria(CategoriaCNH categoria){

        return motoristaRepository.findMotoristaByCategoria(categoria).stream()
                .map(motoristaMapper::toResponse)
                .toList();
    }







}
