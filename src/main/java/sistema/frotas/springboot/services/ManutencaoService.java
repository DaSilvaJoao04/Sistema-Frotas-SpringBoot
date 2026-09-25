package sistema.frotas.springboot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.manutencao.ManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoResponse;
import sistema.frotas.springboot.entities.Manutencao;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.TipoManutencao;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.ManutencaoMapper;
import sistema.frotas.springboot.repositories.ManutencaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final ManutencaoMapper manutencaoMapper;


    public List<ManutencaoResponse> buscarTodasManutencoes(){

         return  manutencaoRepository.findAll().stream()
                 .map(manutencaoMapper::toResponse)
                 .toList();

    }

    public ManutencaoResponse buscarPorId(Long id){

        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada"));

        return manutencaoMapper.toResponse(manutencao);

    }


    public List<ManutencaoResponse> buscarPorPlaca(String placa){

        return  manutencaoRepository.findByPlaca(placa).stream()
                .map(manutencaoMapper::toResponse)
                .toList();

    }

    public List<ManutencaoResponse> buscarPorStatus(StatusManutencao statusManutencao){

        return manutencaoRepository.findByStatus(statusManutencao).stream()
                .map(manutencaoMapper::toResponse)
                .toList();

    }


    public ManutencaoResponse iniciarManutencao(ManutencaoRequest manutencaoRequest){

        Manutencao manutencao = manutencaoMapper.toEntity(manutencaoRequest);

        manutencao.setStatus(StatusManutencao.EM_ANDAMENTO);

        Manutencao manutencaoSalva = manutencaoRepository.save(manutencao);

        return manutencaoMapper.toResponse(manutencaoSalva);

    }

    public ManutencaoResponse encerrarManutencao(Long id, ManutencaoRequest manutencaoRequest){

            Manutencao manutencao = manutencaoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada"));

            manutencao.setDataConclusao(manutencaoRequest.dataConclusao());
            manutencao.setQuilometragem(manutencaoRequest.quilometragem());
            manutencao.setCusto(manutencaoRequest.custo());

            manutencao.setStatus(StatusManutencao.CONCLUIDA);

            Manutencao manutencaoEncerrada = manutencaoRepository.save(manutencao);

            return manutencaoMapper.toResponse(manutencaoEncerrada);


    }

    public ManutencaoResponse cancelarManutencao (Long id){

        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada"));



        manutencao.setStatus(StatusManutencao.CANCELADA);

        Manutencao manutencaoEncerrada = manutencaoRepository.save(manutencao);

        return  manutencaoMapper.toResponse(manutencaoEncerrada);

    }


    public List<ManutencaoResponse> buscarPorPlacaETipo(String placa, TipoManutencao tipoManutencao){

        return manutencaoRepository.findByPlacaAndTipo(placa, tipoManutencao).stream()
                .map(manutencaoMapper::toResponse)
                .toList();



    }











}
