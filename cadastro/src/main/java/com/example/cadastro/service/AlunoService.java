package com.example.cadastro.service;

import com.example.cadastro.model.Aluno;
import com.example.cadastro.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Aluno salvar(Aluno aluno) {
        return repository.save(aluno);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}