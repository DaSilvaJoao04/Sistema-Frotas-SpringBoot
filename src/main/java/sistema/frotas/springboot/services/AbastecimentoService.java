package sistema.frotas.springboot.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoRequest;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoResponse;
import sistema.frotas.springboot.entities.Abastecimento;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.AbastecimentoMapper;
import sistema.frotas.springboot.repositories.AbastecimentoRepository;
import sistema.frotas.springboot.repositories.VeiculoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbastecimentoService {

    private final AbastecimentoRepository abastecimentoRepository;
    private final AbastecimentoMapper abastecimentoMapper;
    private final VeiculoRepository veiculoRepository;


    public List<AbastecimentoResponse> buscarTodosAbastecimentos(){

        return abastecimentoRepository.findAll().stream()
                .map(abastecimentoMapper::toResponse)
                .toList();


    }

    public AbastecimentoResponse buscarAbastecimentoPorId(Long id){

        Abastecimento abastecimento = abastecimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Registro de Abastecimento não encontrado"));

        return abastecimentoMapper.toResponse(abastecimento);

    }


    public AbastecimentoResponse abastecerVeiculo(AbastecimentoRequest request){

        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não localizado"));


        Abastecimento abastecimento = new Abastecimento();

        abastecimento.setVeiculo(veiculo);
        abastecimento.setTipoCombustivel(request.tipoCombustivel());
        abastecimento.setQuantidadeLitros(request.quantidadeLitros());
        abastecimento.setValorPorLitro(request.valorPorLitro());

        BigDecimal valorTotal = request.quantidadeLitros().multiply(request.valorPorLitro());

        abastecimento.setValorTotal(valorTotal);

        Abastecimento carroAbastecido = abastecimentoRepository.save(abastecimento);

        return abastecimentoMapper.toResponse(carroAbastecido);
    }





    public List<AbastecimentoResponse> buscarPorVeiculo(Long veiculoId){
        return abastecimentoRepository.findByVeiculoId(veiculoId).stream()
                .map(abastecimentoMapper::toResponse)
                .toList();

    }

    public List<AbastecimentoResponse> buscarPorData(LocalDate date){

        return abastecimentoRepository.findByDataAbastecimento(date).stream()
                .map(abastecimentoMapper::toResponse)
                .toList();
    }


    public List<AbastecimentoResponse> buscarPorTipoCombustivel(TipoCombustivel tipoCombustivel){

        return abastecimentoRepository.findByTipoCombustivel(tipoCombustivel).stream()
                .map(abastecimentoMapper::toResponse)
                .toList();

    }





}
