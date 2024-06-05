package org.lab.demo2.beans;

public class Job {
    private String jobId;
    private String Titulo;
    private Integer mins;
    private Integer maxs;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Integer getMaxs() {
        return maxs;
    }

    public void setMaxs(Integer maxs) {
        this.maxs = maxs;
    }
}
