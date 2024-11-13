package com.example.library_management_system.modles;

public class MagazineModel extends ResourcesModel{
    private String editor;
    private String issn;
    private int volume;

    public MagazineModel(int id, boolean availalbeState, String title,
                         String publisher, int totalCopies, int copiesLeft,
                         String editor, String issn, int volume) {
        super(id, availalbeState, title, publisher, totalCopies, copiesLeft);
        this.editor = editor;
        this.issn = issn;
        this.volume = volume;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
