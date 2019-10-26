package com.clase;

public class Noticias {
    private String titulo;
    private String subtitulo;

    public Noticias(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
}
