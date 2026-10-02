package sistema.frotas.springboot.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.viagem.FinalizarViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemRequest;
import sistema.frotas.springboot.dto.viagem.ViagemResponse;
import sistema.frotas.springboot.entities.Motorista;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.entities.Viagem;
import sistema.frotas.springboot.enums.StatusMotorista;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.ViagemMapper;
import sistema.frotas.springboot.repositories.MotoristaRepository;
import sistema.frotas.springboot.repositories.VeiculoRepository;
import sistema.frotas.springboot.repositories.ViagemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final ViagemMapper viagemMapper;

    private final VeiculoRepository veiculoRepository;

    private final MotoristaRepository motoristaRepository;

    @Transactional
    public ViagemResponse criarViagem(ViagemRequest request){

        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Veículo não localizado com ID:" + request.veiculoId()));


        Motorista motorista = motoristaRepository.findById(request.motoristaId())
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Motorista não localizado com ID:" + request.motoristaId()));


        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL) {
            throw new IllegalStateException("Veiculo não está disponivel");

        }

        if (motorista.getStatus() != StatusMotorista.ATIVO) {
            throw new IllegalStateException("Motorista não está ativo");

        }

        Viagem viagem = viagemMapper.toEntity(request);

        viagem.setVeiculo(veiculo);
        viagem.setMotorista(motorista);

        veiculo.setStatus(StatusVeiculo.EM_USO);

        veiculoRepository.save(veiculo);

        Viagem viagemSalva = viagemRepository.save(viagem);

        return  viagemMapper.toResponse(viagemSalva);


    }


    public List<ViagemResponse> buscarTodasViagens(){

        return viagemRepository.findAll().stream()
                .map(viagemMapper::toResponse)
                .toList();


    }

    public ViagemResponse buscarViagemPorId(Long id){

        Viagem viagem = viagemRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Erro: Viagem não localizada com ID:" + id));

        return viagemMapper.toResponse(viagem);

    }


    public List<ViagemResponse> buscarViagemPorVeiculo(Long veiculoId){

        if (!veiculoRepository.existsById(veiculoId)){
            throw new ResourceNotFoundException("Erro: Veiculo não localizado com ID:" + veiculoId);
        }

        return viagemRepository.findByVeiculoId(veiculoId).stream()
                .map(viagemMapper::toResponse)
                .toList();

    }

    public List<ViagemResponse> buscarViagemPorMotorista(Long motoristaId){

        if (!motoristaRepository.existsById(motoristaId)){
            throw new ResourceNotFoundException("Erro: Motorista não localizado com ID:" + motoristaId);
        }

        return viagemRepository.findByMotoristaId(motoristaId).stream()
                .map(viagemMapper::toResponse)
                .toList();

    }

    @Transactional
    public ViagemResponse finalizarViagem(Long id, FinalizarViagemRequest request){

        Viagem viagem = viagemRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Erro: Viagem não localizada com ID:" + id));


        if (viagem.getDataChegada() != null){
            throw new IllegalStateException("Erro: Viagem já foi finalizada");
        }

        if (request.kmFinal().compareTo(viagem.getKmInicial()) < 0) {
            throw new IllegalArgumentException("Quilometragem final não pode ser menor que a inicial");
        }

        if (request.dataChegada().isBefore(viagem.getDataSaida())){
            throw new IllegalArgumentException("Data de chegada não pode ser anterior à data de saída");
        }

        Veiculo veiculo = viagem.getVeiculo();

        veiculo.setStatus(StatusVeiculo.DISPONIVEL);

        viagem.setDataChegada(request.dataChegada());

        viagem.setKmFinal(request.kmFinal());

        veiculo.setQuilometragemAtual(viagem.getKmFinal());


        veiculoRepository.save(veiculo);

        Viagem viagemFinalizada = viagemRepository.save(viagem);

        return viagemMapper.toResponse(viagemFinalizada);



    }


}
