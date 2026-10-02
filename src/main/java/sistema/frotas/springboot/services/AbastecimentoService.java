package sistema.frotas.springboot.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoRequest;
import sistema.frotas.springboot.dto.abastecimento.AbastecimentoResponse;
import sistema.frotas.springboot.entities.Abastecimento;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.StatusVeiculo;
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
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Registro de Abastecimento não encontrado com ID:" + id));

        return abastecimentoMapper.toResponse(abastecimento);

    }


    public AbastecimentoResponse abastecerVeiculo(AbastecimentoRequest request){


        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                        .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não localizado com ID:" + request.veiculoId()));


        if (veiculo.getStatus() == StatusVeiculo.INATIVO){
            throw new IllegalStateException("Erro: Esse veículo está inativo");
        }

        if (veiculo.getTipoCombustivel() != request.tipoCombustivel()){
            throw new IllegalArgumentException("Erro: Tipo de combustível incompatível com o veículo ");
        }

        if (request.quantidadeLitros().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Erro: O abastecimento deve ter ao menos 1 litro");
        }

        if (request.dataAbastecimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Erro: Data de abastecimento não pode estar no futuro");
        }

        Abastecimento abastecimento = new Abastecimento();

        abastecimento.setVeiculo(veiculo);
        abastecimento.setTipoCombustivel(request.tipoCombustivel());
        abastecimento.setQuantidadeLitros(request.quantidadeLitros());
        abastecimento.setValorPorLitro(request.valorPorLitro());
        abastecimento.setDataAbastecimento(request.dataAbastecimento());

        BigDecimal valorTotal = request.quantidadeLitros().multiply(request.valorPorLitro());

        abastecimento.setValorTotal(valorTotal);

        Abastecimento carroAbastecido = abastecimentoRepository.save(abastecimento);

        return abastecimentoMapper.toResponse(carroAbastecido);
    }





    public List<AbastecimentoResponse> buscarPorVeiculo(Long veiculoId){

        if (!veiculoRepository.existsById(veiculoId)){
            throw new ResourceNotFoundException("Erro: Veículo não localizado com ID:" + veiculoId);
        }

        return abastecimentoRepository.findByVeiculoId(veiculoId).stream()
                .map(abastecimentoMapper::toResponse)
                .toList();

    }

    public List<AbastecimentoResponse> buscarPorData(LocalDate date){

        LocalDate hoje = LocalDate.now();

        if (date.isAfter(hoje)){
            throw new IllegalArgumentException("Erro: Data de abastecimento não pode estar no futuro");
        }

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
