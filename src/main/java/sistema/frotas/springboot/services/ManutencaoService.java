package sistema.frotas.springboot.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sistema.frotas.springboot.dto.manutencao.FinalizarManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoRequest;
import sistema.frotas.springboot.dto.manutencao.ManutencaoResponse;
import sistema.frotas.springboot.entities.Manutencao;
import sistema.frotas.springboot.entities.Veiculo;
import sistema.frotas.springboot.enums.StatusManutencao;
import sistema.frotas.springboot.enums.StatusVeiculo;
import sistema.frotas.springboot.enums.TipoManutencao;
import sistema.frotas.springboot.exceptions.ResourceNotFoundException;
import sistema.frotas.springboot.mapper.ManutencaoMapper;
import sistema.frotas.springboot.repositories.ManutencaoRepository;
import sistema.frotas.springboot.repositories.VeiculoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;

    private final VeiculoRepository veiculoRepository;

    private final ManutencaoMapper manutencaoMapper;


    public List<ManutencaoResponse> buscarTodasManutencoes(){

         return  manutencaoRepository.findAll().stream()
                 .map(manutencaoMapper::toResponse)
                 .toList();

    }

    public ManutencaoResponse buscarPorId(Long id){

        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada com ID:" + id));

        return manutencaoMapper.toResponse(manutencao);

    }


    public List<ManutencaoResponse> buscarPorPlaca(String placa){

        if (!veiculoRepository.existsByPlaca(placa)){
            throw new ResourceNotFoundException("Erro: Não há veículo registrado com essa placa: " + placa);
        }

        return  manutencaoRepository.findByPlaca(placa).stream()
                .map(manutencaoMapper::toResponse)
                .toList();

    }

    public List<ManutencaoResponse> buscarPorStatus(StatusManutencao statusManutencao){

        return manutencaoRepository.findByStatus(statusManutencao).stream()
                .map(manutencaoMapper::toResponse)
                .toList();

    }

    @Transactional
    public ManutencaoResponse iniciarManutencao(ManutencaoRequest request){


        Veiculo veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Não foi localizado veículo com ID: " + request.veiculoId()));


        if (veiculo.getStatus() == StatusVeiculo.INATIVO) {
            throw new IllegalStateException("Erro: Não é possível iniciar manutenção em um veículo inativo");
        }

        if (veiculo.getStatus() != StatusVeiculo.DISPONIVEL){
            throw new IllegalStateException("Erro: Não é possível iniciar manutenção em um veículo indisponível");
        }


        Manutencao manutencao = manutencaoMapper.toEntity(request);

        manutencao.setVeiculo(veiculo);
        manutencao.setStatus(StatusManutencao.EM_ANDAMENTO);

        veiculo.setStatus(StatusVeiculo.EM_MANUTENCAO);

        Manutencao manutencaoSalva = manutencaoRepository.save(manutencao);

        veiculoRepository.save(veiculo);

        return manutencaoMapper.toResponse(manutencaoSalva);

    }

    @Transactional
    public ManutencaoResponse encerrarManutencao(Long id, FinalizarManutencaoRequest request){


            Manutencao manutencao = manutencaoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada com ID:" + id));

            if (manutencao.getStatus() == StatusManutencao.AGENDADA){
                throw new IllegalStateException("Erro: Não é possível encerrar uma manutenção futura");
            }

            if (manutencao.getStatus() == StatusManutencao.CONCLUIDA){
                throw new IllegalStateException("Erro: Não é possível encerrar uma manutenção concluída");
            }

            if (manutencao.getStatus() == StatusManutencao.CANCELADA){
                throw new IllegalStateException("Erro: Não é possível encerrar uma manutenção cancelada");
            }

            if (request.dataConclusao().isBefore(manutencao.getDataEntrada())){
                throw new IllegalStateException("Erro: Data de conclusão deve ser posterior a data de entrada");
            }

            Veiculo veiculo = manutencao.getVeiculo();

            if (request.quilometragem().compareTo(veiculo.getQuilometragemAtual()) < 0){
                throw new IllegalArgumentException
                        ("Erro: Quilometragem da manutenção não pode ser inferior à quilometragem atual do veículo");
            }

            manutencao.setDataConclusao(request.dataConclusao());
            manutencao.setQuilometragem(request.quilometragem());
            manutencao.setCusto(request.custo());
            manutencao.setStatus(StatusManutencao.CONCLUIDA);

            veiculo.setQuilometragemAtual(request.quilometragem());
            veiculo.setStatus(StatusVeiculo.DISPONIVEL);

            Manutencao manutencaoEncerrada = manutencaoRepository.save(manutencao);

            veiculoRepository.save(veiculo);

            return manutencaoMapper.toResponse(manutencaoEncerrada);


    }

    public ManutencaoResponse cancelarManutencao (Long id){

        Manutencao manutencao = manutencaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Manutenção não encontrada com ID:" + id));

        if (manutencao.getStatus() == StatusManutencao.CONCLUIDA){
            throw new IllegalStateException("Erro: Não é possível cancelar uma manutenção concluída");
        }

        if (manutencao.getStatus() == StatusManutencao.CANCELADA){
            throw new IllegalStateException("Erro: Essa manutenção já foi cancelada");
        }

        Veiculo veiculo = manutencao.getVeiculo();

        manutencao.setStatus(StatusManutencao.CANCELADA);
        veiculo.setStatus(StatusVeiculo.DISPONIVEL);

        Manutencao manutencaoCancelada = manutencaoRepository.save(manutencao);

        return  manutencaoMapper.toResponse(manutencaoCancelada);

    }


    public List<ManutencaoResponse> buscarPorPlacaETipo(String placa, TipoManutencao tipoManutencao){

        if (!veiculoRepository.existsByPlaca(placa)){
            throw new ResourceNotFoundException("Erro: Não há veículo registrado com essa placa: " + placa);
        }

        return manutencaoRepository.findByPlacaAndTipo(placa, tipoManutencao).stream()
                .map(manutencaoMapper::toResponse)
                .toList();



    }











}
