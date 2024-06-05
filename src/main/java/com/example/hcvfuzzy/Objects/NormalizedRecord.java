package com.example.hcvfuzzy.Objects;

public class NormalizedRecord {
    private int ID;
    private Interval NormalizedRadius;
    private Interval NormalizedTexture;
    private Interval NormalizedPerimeter;
    private Interval NormalizedArea;
    private Interval NormalizedSmoothness;
    private Interval NormalizedCompactness;
    private Interval NormalizedConcavity;
    private Interval NormalizedConcavePoints;
    private Interval NormalizedSymmetry;
    private Interval NormalizedFractalDimension;
    private int Decision;

    public NormalizedRecord() {

    }

    public NormalizedRecord(NormalizedRecord normalizedRecord) {
        this.ID = normalizedRecord.ID;
        this.NormalizedRadius = normalizedRecord.NormalizedRadius;
        this.NormalizedTexture = normalizedRecord.NormalizedTexture;
        this.NormalizedPerimeter = normalizedRecord.NormalizedPerimeter;
        this.NormalizedArea = normalizedRecord.NormalizedArea;
        this.NormalizedSmoothness = normalizedRecord.NormalizedSmoothness;
        this.NormalizedCompactness = normalizedRecord.NormalizedCompactness;
        this.NormalizedConcavity = normalizedRecord.NormalizedConcavity;
        this.NormalizedConcavePoints = normalizedRecord.NormalizedConcavePoints;
        this.NormalizedSymmetry = normalizedRecord.NormalizedSymmetry;
        this.NormalizedFractalDimension =normalizedRecord.NormalizedFractalDimension;
        this.Decision = normalizedRecord.Decision;
    }
    public NormalizedRecord copy() {
        NormalizedRecord copy = new NormalizedRecord();
        copy.setID(this.getID());
        copy.setNormalizedRadius(this.getNormalizedRadius());
        copy.setNormalizedTexture(this.getNormalizedTexture());
        copy.setNormalizedPerimeter(this.getNormalizedPerimeter());
        copy.setNormalizedArea(this.getNormalizedArea());
        copy.setNormalizedSmoothness(this.getNormalizedSmoothness());
        copy.setNormalizedCompactness(this.getNormalizedCompactness());
        copy.setNormalizedConcavity(this.getNormalizedConcavity());
        copy.setNormalizedConcavePoints(this.getNormalizedConcavePoints());
        copy.setNormalizedSymmetry(this.getNormalizedSymmetry());
        copy.setNormalizedFractalDimension(this.getNormalizedFractalDimension());
        copy.setDecision(this.getDecision());
        return copy;
    }

    public NormalizedRecord(Record record) {
    }

    public Interval getAttributeValue(String attributeName) {
        switch (attributeName) {
            case "radius":
                return NormalizedRadius;
            case "texture":
                return NormalizedTexture;
            case "perimeter":
                return NormalizedPerimeter;
            case "area":
                return NormalizedArea;
            case "smoothness":
                return NormalizedSmoothness;
            case "compactness":
                return NormalizedCompactness;
            case "concavity":
                return NormalizedConcavity;
            case "concavePoints":
                return NormalizedConcavePoints;
            case "symmetry":
                return NormalizedSymmetry;
            case "fractalDimension":
                return NormalizedFractalDimension;
            default:
                throw new IllegalArgumentException("Nieprawidłowa nazwa atrybutu: " + attributeName);
        }
    }

    public void setAttributeValue(String attributeName, Interval normalizedValue) {
        switch (attributeName) {
            case "radius":
                setNormalizedRadius(normalizedValue);
                break;
            case "texture":
                setNormalizedTexture(normalizedValue);
                break;
            case "perimeter":
                setNormalizedPerimeter(normalizedValue);
                break;
            case "area":
                setNormalizedArea(normalizedValue);
                break;
            case "smoothness":
                setNormalizedSmoothness(normalizedValue);
                break;
            case "compactness":
                setNormalizedCompactness(normalizedValue);
                break;
            case "concavity":
                setNormalizedConcavity(normalizedValue);
                break;
            case "concavePoints":
                setNormalizedConcavePoints(normalizedValue);
                break;
            case "symmetry":
                setNormalizedSymmetry(normalizedValue);
                break;
            case "fractalDimension":
                setNormalizedFractalDimension(normalizedValue);
                break;
            default:
                throw new IllegalArgumentException("Nieprawidłowa nazwa atrybutu: " + attributeName);
        }
    }
    public Interval[] getAttributes() {
        return new Interval[]{
                NormalizedRadius,
                NormalizedTexture,
                NormalizedPerimeter,
                NormalizedArea,
                NormalizedSmoothness,
                NormalizedCompactness,
                NormalizedConcavity,
                NormalizedConcavePoints,
                NormalizedSymmetry,
                NormalizedFractalDimension
        };
    }

    //sprawdz czy w rekodzie jest brakująca dana(bez nazw tylko wartosci)
    public boolean containsMissingValue(Interval[] attributes) {
        for (Interval attribute : attributes) {
            if (attribute == null) {
                return true;
            }
        }
        return false;

    }
    public NormalizedRecord complement(){
        String[] attributeNames = {
                "radius",
                "texture",
                "perimeter",
                "area",
                "smoothness",
                "compactness",
                "concavity",
                "concavePoints",
                "symmetry",
                "fractalDimension"
        };
        NormalizedRecord newNormalizedRecord = new NormalizedRecord();
        for(String name:attributeNames){
            Interval intervalValues = getAttributeValue(name);

//            System.out.println(Arrays.toString(intervalValues) + name);
            newNormalizedRecord.setAttributeValue(name,intervalValues);
        }
        return newNormalizedRecord;
    }
    //sprawdz w ktory atrybut jest brakujący i wypisz jego nazwe
    public String getMissingAttributeName() {
        String[] attributeNames = {
                "radius",
                "texture",
                "perimeter",
                "area",
                "smoothness",
                "compactness",
                "concavity",
                "concavePoints",
                "symmetry",
                "fractalDimension"
        };

        Interval[] attributes = getAttributes();

        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] == null) {
                return attributeNames[i];
            }
        }

        return null; // Zwraca null, jeśli nie znaleziono brakującej danej
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Interval getNormalizedRadius() {
        return NormalizedRadius;
    }

    public void setNormalizedRadius(Interval normalizedRadius) {
        NormalizedRadius = normalizedRadius;
    }

    public Interval getNormalizedTexture() {
        return NormalizedTexture;
    }

    public void setNormalizedTexture(Interval normalizedTexture) {
        NormalizedTexture = normalizedTexture;
    }

    public Interval getNormalizedPerimeter() {
        return NormalizedPerimeter;
    }

    public void setNormalizedPerimeter(Interval normalizedPerimeter) {
        NormalizedPerimeter = normalizedPerimeter;
    }

    public Interval getNormalizedArea() {
        return NormalizedArea;
    }

    public void setNormalizedArea(Interval normalizedArea) {
        NormalizedArea = normalizedArea;
    }

    public Interval getNormalizedSmoothness() {
        return NormalizedSmoothness;
    }

    public void setNormalizedSmoothness(Interval normalizedSmoothness) {
        NormalizedSmoothness = normalizedSmoothness;
    }

    public Interval getNormalizedCompactness() {
        return NormalizedCompactness;
    }

    public void setNormalizedCompactness(Interval normalizedCompactness) {
        NormalizedCompactness = normalizedCompactness;
    }

    public Interval getNormalizedConcavity() {
        return NormalizedConcavity;
    }

    public void setNormalizedConcavity(Interval normalizedConcavity) {
        NormalizedConcavity = normalizedConcavity;
    }

    public Interval getNormalizedConcavePoints() {
        return NormalizedConcavePoints;
    }

    public void setNormalizedConcavePoints(Interval normalizedConcavePoints) {
        NormalizedConcavePoints = normalizedConcavePoints;
    }

    public Interval getNormalizedSymmetry() {
        return NormalizedSymmetry;
    }

    public void setNormalizedSymmetry(Interval normalizedSymmetry) {
        NormalizedSymmetry = normalizedSymmetry;
    }

    public Interval getNormalizedFractalDimension() {
        return NormalizedFractalDimension;
    }

    public void setNormalizedFractalDimension(Interval normalizedFractalDimension) {
        NormalizedFractalDimension = normalizedFractalDimension;
    }

    public int getDecision() {
        return Decision;
    }

    public void setDecision(int decision) {
        Decision = decision;
    }
}