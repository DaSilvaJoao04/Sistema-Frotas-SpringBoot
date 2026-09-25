package sistema.frotas.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.multa.MultaRequest;
import sistema.frotas.springboot.dto.multa.MultaResponse;
import sistema.frotas.springboot.dto.multa.PagarMultaRequest;
import sistema.frotas.springboot.entities.Multa;
import sistema.frotas.springboot.enums.StatusMulta;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.MultaMapper;
import sistema.frotas.springboot.repositories.MultaRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;
    private final MultaMapper multaMapper;


    public List<MultaResponse> buscarTodasMultas(){

        return multaRepository.findAll().stream()
                .map(multaMapper::toResponse)
                .toList();

    }

    public MultaResponse buscarPorId(Long id){

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Multa não localizada"));

        return multaMapper.toResponse(multa);

    }

    public MultaResponse criarMulta(MultaRequest multaRequest){

          Multa multa = multaMapper.toEntity(multaRequest);

          Multa multaCriada = multaRepository.save(multa);

          return multaMapper.toResponse(multaCriada);

    }

    public MultaResponse pagarMulta(Long id, PagarMultaRequest request){

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Multa não localizada"));

        if (multa.getStatus() == StatusMulta.PAGA){
            throw new IllegalStateException("Erro: A multa já está paga");
        }

        if (request.valor() == null || request.valor().compareTo(multa.getCusto()) !=  0){
            throw new IllegalStateException("Erro: O valor deve ser igual ao custo da multa");
        }

        multa.setDataPagamento(request.dataPagamento());
        multa.setStatus(StatusMulta.PAGA);

        Multa multaPaga = multaRepository.save(multa);

        return multaMapper.toResponse(multaPaga);

    }

    public List<MultaResponse> buscarMultaPorPlaca(String placa){

         return multaRepository.findByVeiculoPlaca(placa).stream()
                 .map(multaMapper::toResponse)
                 .toList();

    }

    public List<MultaResponse> buscarMultaPorStatus(StatusMulta statusMulta){

        return multaRepository.findByStatus(statusMulta).stream()
                .map(multaMapper::toResponse)
                .toList();

    }



}
