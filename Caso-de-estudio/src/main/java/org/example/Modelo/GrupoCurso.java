package org.example.Modelo;
public class GrupoCurso {
    private int id;
    private int grupoId;
    private int cursoId;

    public GrupoCurso(int id, int grupoId, int cursoId) {
        this.id = id;
        this.grupoId = grupoId;
        this.cursoId = cursoId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getGrupoId() { return grupoId; }
    public void setGrupoId(int grupoId) { this.grupoId = grupoId; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }
}
