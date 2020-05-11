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
public class Matter {
    private List<FileData> files;
    private String differentiator;
    private List<TotalDifferentiator> result = new ArrayList<>();

    public Matter() {
    }

    public Matter(List<FileData> files, String differentiator) {
        this.files = files;
        this.differentiator = differentiator;
    }

    public List<FileData> getFiles() {
        return files;
    }

    public void setFiles(List<FileData> files) {
        this.files = files;
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
