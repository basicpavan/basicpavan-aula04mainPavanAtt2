package com.example.cadastro.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Aluno {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Digite um e-mail válido")
    private String email;

    @NotBlank(message = "A matrícula é obrigatória")
    private String matricula;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    public Aluno() {
    }

    public Aluno(String nome, String email, String matricula, String curso) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.curso = curso;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
}