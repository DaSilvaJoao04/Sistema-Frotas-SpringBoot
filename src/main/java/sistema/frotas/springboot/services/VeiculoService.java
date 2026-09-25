package sistema.frotas.springboot.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.veiculo.VeiculoAtualizacaoRequest;
import sistema.frotas.springboot.dto.veiculo.VeiculoRequest;
import sistema.frotas.springboot.dto.veiculo.VeiculoResponse;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoCombustivel;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.VeiculoMapper;
import sistema.frotas.springboot.repositories.VeiculoRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;

    // Basicos
    public List<VeiculoResponse> findAll () {

        return veiculoRepository.findAll().stream()
                   .map(veiculoMapper::toResponse)
                   .toList();
    }

    public VeiculoResponse findById(Long id){
       Veiculo veiculo = veiculoRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não encontrado"));

        return veiculoMapper.toResponse(veiculo);
    }

    public VeiculoResponse criarVeiculo(VeiculoRequest request){

        Veiculo veiculo = veiculoMapper.toEntity(request);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        return veiculoMapper.toResponse(veiculoSalvo);


    }

    public VeiculoResponse atualizarVeiculo(Long id, VeiculoAtualizacaoRequest request){

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não encontrado"));

        veiculo.setQuilometragemAtual(request.quilometragemAtual());
        veiculo.setStatus(request.status());

       Veiculo alterado = veiculoRepository.save(veiculo);

       return veiculoMapper.toResponse(alterado);


    }

    public void inativarVeiculo (Long id){

        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não encontrado"));

        veiculo.setStatus(StatusVeiculo.INATIVO);

        veiculoRepository.save(veiculo);

    }



    // Consultas

    public VeiculoResponse buscarPelaPlaca(String placa){

        Veiculo veiculo = veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Veiculo não encontrado"));

        return veiculoMapper.toResponse(veiculo);

    }

    public List<VeiculoResponse> buscarPorStatus(StatusVeiculo statusVeiculo){

        return  veiculoRepository.findByStatus(statusVeiculo).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }

    public List<VeiculoResponse> buscarPorCombustivel(TipoCombustivel tipoCombustivel){

        return  veiculoRepository.findByCombustivel(tipoCombustivel).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }

    public List<VeiculoResponse> buscarPelaMarca(String marca){

        return  veiculoRepository.findByMarca(marca).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }

    public List<VeiculoResponse> buscarPorAnoFabricacao(Integer ano){

        return  veiculoRepository.findByAnoFabricacao(ano).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }


    // Consultas Aprimoradas

    public List<VeiculoResponse> buscarPorStatusECombustivel (StatusVeiculo statusVeiculo, TipoCombustivel tipoCombustivel){
        return  veiculoRepository.findByStatusAndCombustivel(statusVeiculo, tipoCombustivel).stream()
                .map(veiculoMapper::toResponse)
                .toList();


    }

    public List<VeiculoResponse> buscarPorAnoFabricacaoGreaterThanEqual(Integer ano){

        return veiculoRepository.findByAnoFabricacaoGreaterThanEqual(ano).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }

    public List<VeiculoResponse> buscarPorQuilometragemlGreaterThan(Long quilometragem){

        return  veiculoRepository.findByQuilometragemAtualGreaterThan(quilometragem).stream()
                .map(veiculoMapper::toResponse)
                .toList();

    }
}
