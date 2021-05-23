package com.imz.imz_example_mod;

public class Tag {
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String tagName;
    private String ID;

    public String getRarity() {
        return rarity.name;
    }

    public void setRarity(String rarity) {
        switch (rarity){
            case "normal":
                this.rarity = TagRarity.NORMAL;
                break;
            case "rare":
                this.rarity = TagRarity.RARE;
                break;
            case "epic":
                this.rarity = TagRarity.EPIC;
                break;
            default:
                this.rarity = TagRarity.NORMAL;

        }

    }

    private TagRarity rarity;

    public Tag(String tagName, String ID,String rarity) {
        this.tagName = tagName;
        this.ID = ID;
        switch (rarity){
            case "normal":
                this.rarity = TagRarity.NORMAL;
                break;
            case "rare":
                this.rarity = TagRarity.RARE;
                break;
            case "epic":
                this.rarity = TagRarity.EPIC;
                break;
            default:
                this.rarity = TagRarity.NORMAL;

        }
    }

    public enum TagRarity {
        NORMAL("normal"),RARE("rare"),EPIC("epic");
        private String name;
        TagRarity(String name){
            this.name = name;
        }
    }

    public static float getEpicScale(){
        return 4.0f;
    }

    public static float getRareScale(){
        return 2.0f;
    }
}
