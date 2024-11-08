package org.campusconnect.estudafacil.service;

import lombok.RequiredArgsConstructor;
import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.campusconnect.estudafacil.repository.FichaIndividualAlocacaoDiscenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaIndividualAlocacaoDiscenteService {

    private final FichaIndividualAlocacaoDiscenteRepository fichaIndividualAlocacaoDiscenteRepository;
    private final TurmaUnidadeCurricularService turmaUnidadeCurricularService;

    @Transactional
    public FichaIndividualAlocacaoDiscente cadastrarFichaIndividualAlocacaoDiscente() {
        FichaIndividualAlocacaoDiscente fichaIndividualAlocacaoDiscente = new FichaIndividualAlocacaoDiscente();
/*        List<Nota> boletimFicha = Stream.generate(() -> new Nota(0.0f)).limit(4).toList();
        fichaIndividualAlocacaoDiscente.setNotas(boletimFicha);
        fichaIndividualAlocacaoDiscente.setPresencas(new ArrayList<>());*/
        fichaIndividualAlocacaoDiscente.setNotaUnidade1(0.0f);
        fichaIndividualAlocacaoDiscente.setNotaUnidade2(0.0f);
        fichaIndividualAlocacaoDiscente.setNotaUnidade3(0.0f);
        fichaIndividualAlocacaoDiscente.setNotaReposicao(0.0f);
        fichaIndividualAlocacaoDiscente.setFaltas(0);
        fichaIndividualAlocacaoDiscente.setResultadoFinal(0.0f);
        fichaIndividualAlocacaoDiscente.setPorcentagemFrequencia(0.0f);
        fichaIndividualAlocacaoDiscenteRepository.save(fichaIndividualAlocacaoDiscente);
        return fichaIndividualAlocacaoDiscente;
    }

    @Transactional
    public String gravarNotasFrequencias(List<FichaIndividualAlocacaoDiscente> fichas) {
        registrarNotas(fichas);
        registrarFaltas(fichas);
        return "Notas e faltas registradas com sucesso.";
    }

    @Transactional
    public void registrarNotas(List<FichaIndividualAlocacaoDiscente> fichas) {
        if (!fichas.isEmpty()) {
            List<FichaIndividualAlocacaoDiscente> fichasExistentes = new ArrayList<>();

            for (FichaIndividualAlocacaoDiscente ficha : fichas) {
                FichaIndividualAlocacaoDiscente fichaExistente = fichaIndividualAlocacaoDiscenteRepository.findById(ficha.getId()).orElse(null);
                if (fichaExistente != null) {
                    fichaExistente.setNotaUnidade1(tratarNota(ficha.getNotaUnidade1()));
                    fichaExistente.setNotaUnidade2(tratarNota(ficha.getNotaUnidade2()));
                    fichaExistente.setNotaUnidade3(tratarNota(ficha.getNotaUnidade3()));
                    fichaExistente.setNotaReposicao(tratarNota(ficha.getNotaReposicao()));

                    fichaExistente.setResultadoFinal(calcularResultadoFinal(
                            fichaExistente.getNotaUnidade1(),
                            fichaExistente.getNotaUnidade2(),
                            fichaExistente.getNotaUnidade3(),
                            fichaExistente.getNotaReposicao()
                    ));
                    fichasExistentes.add(fichaExistente);
                }
            }
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasExistentes);
        }
    }

    private float tratarNota(float nota) {
        if (nota > 10) {
            nota = nota / 10;
        }
        if (nota < 0) {
            return 0.0F;
        }
        return (float) (Math.round(nota * 10) / 10.0);
    }

    @Transactional
    public void registrarFaltas(List<FichaIndividualAlocacaoDiscente> fichas) {
        if (!fichas.isEmpty()) {
            List<FichaIndividualAlocacaoDiscente> fichasExistentes = new ArrayList<>();
            fichas.forEach(ficha -> {
                FichaIndividualAlocacaoDiscente fichaExistente = fichaIndividualAlocacaoDiscenteRepository.findById(ficha.getId()).orElse(null);
                if (fichaExistente != null) {
                    fichaExistente.setFaltas(ficha.getFaltas());
                    fichasExistentes.add(fichaExistente);
                }
            });
            calcularPorcentagemFrequencia(fichasExistentes);
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasExistentes);
        }
    }

    public float calcularResultadoFinal(float notaUnidade1, float notaUnidade2, float notaUnidade3, float notaReposicao) {
        // Criação de uma lista para simular as notas (com exceção da reposição)
        List<Float> notas = new ArrayList<>();
        notas.add(notaUnidade1);
        notas.add(notaUnidade2);
        notas.add(notaUnidade3);

        if (notaReposicao > 0) {
            Float menorNota = Collections.min(notas);

            if (notaReposicao > menorNota) {
                notas.remove(menorNota);
                notas.add(notaReposicao);
            }
        }

        float resultadoFinal = (notas.get(0) + notas.get(1) + notas.get(2)) / 3;

        return Math.round(resultadoFinal * 10) / 10.0f;
    }

    private void calcularPorcentagemFrequencia(List<FichaIndividualAlocacaoDiscente> fichas) {
        if (fichas == null || fichas.isEmpty()) {
            return;
        }
        long idFicha = fichas.stream()
                .map(FichaIndividualAlocacaoDiscente::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lista de fichas está vazia"));
        //TurmaUnidadeCurricular turmaUnidadeCurricular = turmaUnidadeCurricularService.getTurmaByIdFichaIndividual(idFicha);
        //int qtdDiasAulas = turmaUnidadeCurricularService.calcularDiasDeAulaNoSemestre(turmaUnidadeCurricular);
        int qtdDiasAulas = 30;
        fichas.forEach(ficha -> {
            int totalFaltas = ficha.getFaltas();
            float porcentagemFrequencia = ((float) (qtdDiasAulas - totalFaltas) / qtdDiasAulas) * 100;
            ficha.setPorcentagemFrequencia(porcentagemFrequencia);
        });
    }

/*    @Transactional
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
    }*/

/*    @Transactional
    public String registrarFaltas(HashMap<Long, Boolean> presencas) {
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
            });
            calcularPorcentagemFrequencia(fichasIndividuais);
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasIndividuais);
            return "Registro de presenças atualizado com sucesso.";
        }
        return "Houve um problema ao registrar as presenças. Por favor contate o suporte.";
    }*/

}