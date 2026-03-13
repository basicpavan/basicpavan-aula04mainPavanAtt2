package com.example.cadastro.repository;

import com.example.cadastro.model.Aluno;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AlunoRepository {

    private final Map<Long, Aluno> alunos = new LinkedHashMap<>();
    private Long nextId = 1L;

    public AlunoRepository() {
        save(new Aluno("Ana Silva", "ana@email.com", "2024001", "Engenharia de Software"));
        save(new Aluno("Carlos Souza", "carlos@email.com", "2024002", "Análise e Desenv. de Sistemas"));
    }

    public Aluno save(Aluno aluno) {
        if (aluno.getId() == null) {
            aluno.setId(nextId++);
        }
        alunos.put(aluno.getId(), aluno);
        return aluno;
    }

    public List<Aluno> findAll() {
        return new ArrayList<>(alunos.values());
    }

    public Optional<Aluno> findById(Long id) {
        return Optional.ofNullable(alunos.get(id));
    }

    public void deleteById(Long id) {
        alunos.remove(id);
    }
}