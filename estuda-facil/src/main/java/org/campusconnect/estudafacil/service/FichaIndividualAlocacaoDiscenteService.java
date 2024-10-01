package org.campusconnect.estudafacil.service;

import org.campusconnect.estudafacil.entity.FichaIndividualAlocacaoDiscente;
import org.campusconnect.estudafacil.entity.Nota;
import org.campusconnect.estudafacil.repository.FichaIndividualAlocacaoDiscenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FichaIndividualAlocacaoDiscenteService {

    private final FichaIndividualAlocacaoDiscenteRepository fichaIndividualAlocacaoDiscenteRepository;

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
        fichaIndividualAlocacaoDiscente.setResultadoFinal(0.0f);
        fichaIndividualAlocacaoDiscente.setFaltas(0);
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
                IntStream.range(0, novasNotas.size()).forEach(i -> {
                    notasExistentes.get(i).setValor(novasNotas.get(i));
                });
                ficha.setResultadoFinal(calcularResultadoFinal(notasExistentes));
            });
            fichaIndividualAlocacaoDiscenteRepository.saveAll(fichasIndividuais);
            return "Notas registradas com sucesso.";
        }
        return "Houve um problema ao registrar as notas. Por favor contate o suporte.";
    }

}