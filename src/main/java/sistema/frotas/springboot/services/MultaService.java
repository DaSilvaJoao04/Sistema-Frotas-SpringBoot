package sistema.frotas.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.multa.MultaRequest;
import sistema.frotas.springboot.dto.multa.MultaResponse;
import sistema.frotas.springboot.dto.multa.PagarMultaRequest;
import sistema.frotas.springboot.entities.Multa;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.StatusMulta;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.MultaMapper;
import sistema.frotas.springboot.repositories.MultaRepository;
import sistema.frotas.springboot.repositories.VeiculoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MultaService {

    private final MultaRepository multaRepository;

    private final VeiculoRepository veiculoRepository;
    private final MultaMapper multaMapper;


    public List<MultaResponse> buscarTodasMultas(){

        return multaRepository.findAll().stream()
                .map(multaMapper::toResponse)
                .toList();

    }

    public MultaResponse buscarPorId(Long id){

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Multa não localizada com ID:" + id));

        return multaMapper.toResponse(multa);

    }

    public MultaResponse criarMulta(MultaRequest request){

          LocalDate hoje = LocalDate.now();

          if (request.dataInfracao().isAfter(hoje)){
              throw new IllegalArgumentException("Erro: Data da infração não pode ser no futuro");
          }

          Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                  .orElseThrow(() -> new ResourceNotFoundException("Erro: Não foi localizado veículo com ID:" + request.veiculoId()));

          if (veiculo.getStatus() == StatusVeiculo.INATIVO){
              throw new IllegalStateException("Erro: Não é possivel criar uma multa para um veículo inativo");
          }

          Multa multa = multaMapper.toEntity(request);
          multa.setVeiculo(veiculo);

          Multa multaCriada = multaRepository.save(multa);


          return multaMapper.toResponse(multaCriada);

    }

    public MultaResponse pagarMulta(Long id, PagarMultaRequest request){

        Multa multa = multaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Multa não localizada com ID:" + id));

        if (multa.getStatus() == StatusMulta.PAGA){
            throw new IllegalStateException("Erro: A multa já está paga");
        }

        if (multa.getStatus() == StatusMulta.CANCELADA){
            throw new IllegalStateException("Erro: Não é possível pagar uma multa cancelada");
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

         if(!veiculoRepository.existsByPlaca(placa)){
             throw new ResourceNotFoundException("Erro: Não foi localizado veículo com essa placa: " + placa);
         }

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
