package com.thebrotherscode.warnme.twitter.api;

public class MeteoAlert {
    private Long id;
    private int level;
    private String category;

    public MeteoAlert(Long id, int level, String category) {
        this.id = id;
        this.level = level;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
