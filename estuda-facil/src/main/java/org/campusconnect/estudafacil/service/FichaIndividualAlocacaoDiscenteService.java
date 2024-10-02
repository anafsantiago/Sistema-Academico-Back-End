package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.Nota;
import org.campusconnect.estudafacil.entity.Presenca;
import org.campusconnect.estudafacil.entity.TurmaUnidadeCurricular;
import org.campusconnect.estudafacil.repository.FichaIndividualAlocacaoDiscenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FichaIndividualAlocacaoDiscenteService {

    private final FichaIndividualAlocacaoDiscenteRepository fichaIndividualAlocacaoDiscenteRepository;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;

    public float calcularResultadoFinal(List<Nota> boletimFicha) {
        return boletimFicha != null && !boletimFicha.isEmpty()
                ? (float) boletimFicha.stream()
                .mapToDouble(Nota::getValor)
                .average()
                .orElse(0.0)
                : 0.0f;
    }

    @Transactional
    public FichaIndividualAlocacaoDiscente cadastrarFichaIndividualAlocacaoDiscente() {
        FichaIndividualAlocacaoDiscente fichaIndividualAlocacaoDiscente = new FichaIndividualAlocacaoDiscente();
        List<Nota> boletimFicha = Stream.generate(() -> new Nota(0.0f)).limit(3).toList();
        fichaIndividualAlocacaoDiscente.setNotas(boletimFicha);
        fichaIndividualAlocacaoDiscente.setPresencas(new ArrayList<>());
        fichaIndividualAlocacaoDiscente.setFaltas(0);
        fichaIndividualAlocacaoDiscente.setResultadoFinal(0.0f);
        fichaIndividualAlocacaoDiscente.setPorcentagemFrequencia(0.0f);
        fichaIndividualAlocacaoDiscenteRepository.save(fichaIndividualAlocacaoDiscente);
        return fichaIndividualAlocacaoDiscente;
    }

    @Transactional
    public String registrarNotas(HashMap<Long, List<Float>> notas) {
        List<Long> idsFichas = new ArrayList<>(notas.keySet());
        List<FichaIndividualAlocacaoDiscente> fichasIndividuais = fichaIndividualAlocacaoDiscenteRepository.findNotasByIdsFichas(idsFichas);
        if (!fichasIndividuais.isEmpty()) {
            fichasIndividuais.forEach(ficha -> {
                List<Float> novasNotas = notas.get(ficha.getId());
                List<Nota> notasExistentes = ficha.getNotas();
                IntStream.range(0, novasNotas.size()).forEach(i -> notasExistentes.get(i).setValor(novasNotas.get(i)));
                ficha.setResultadoFinal(calcularResultadoFinal(notasExistentes));
            });
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasIndividuais);
            return "Notas registradas com sucesso.";
        }
        return "Houve um problema ao registrar as notas. Por favor contate o suporte.";
    }

    @Transactional
    public String registrarPresencas(HashMap<Long, Boolean> presencas) {
        List<Long> idsFichas = new ArrayList<>(presencas.keySet());
        List<FichaIndividualAlocacaoDiscente> fichasIndividuais = fichaIndividualAlocacaoDiscenteRepository.findPresencasByIdsFichas(idsFichas);
        if (!fichasIndividuais.isEmpty()) {
            fichasIndividuais.forEach(ficha -> {
                Presenca novaPresenca = new Presenca();
                novaPresenca.setPresente(presencas.get(ficha.getId()));
                if (!novaPresenca.isPresente())
                    ficha.setFaltas(ficha.getFaltas() + 1);
                novaPresenca.setData(LocalDate.now());
                ficha.getPresencas().add(novaPresenca);
                calcularPorcentagemFrequencia(ficha);
            });
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasIndividuais);
            return "Registro de presenças atualizado com sucesso.";
        }
        return "Houve um problema ao registrar as presenças. Por favor contate o suporte.";
    }

    private void calcularPorcentagemFrequencia(FichaIndividualAlocacaoDiscente ficha) {
        TurmaUnidadeCurricular turmaUnidadeCurricular = turmaUnidadeCurricularService.getTurmaByIdFichaIndividual(ficha.getId());
        int qtdDiasAulas = turmaUnidadeCurricularService.calcularDiasDeAulaNoSemestre(turmaUnidadeCurricular);
        int totalFaltas = ficha.getFaltas();
        float porcentagemFrequencia = ((float)(qtdDiasAulas - totalFaltas) / qtdDiasAulas) * 100;
        ficha.setPorcentagemFrequencia(porcentagemFrequencia);
    }

}