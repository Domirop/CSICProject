/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Atomo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author domit
 */
public class Molecule {
    private List<FileData> filesData;
    private String differentiator;
    private List<TotalDifferentiator> result = new ArrayList<>();

    public Molecule() {
    }

    public Molecule(List<FileData> files, String differentiator) {
        this.filesData = files;
        this.differentiator = differentiator;
    }

    public List<FileData> getFilesData() {
        return filesData;
    }

    public void setFilesData(List<FileData> filesData) {
        this.filesData = filesData;
    }

    public String getDifferentiator() {
        return differentiator;
    }

    public void setDifferentiator(String differentiator) {
        this.differentiator = differentiator;
    }

    public List<TotalDifferentiator> getResult() {
        return result;
    }

    public void setResult(List<TotalDifferentiator> result) {
        this.result = result;
    }
    
}
