package mytextandspeech.com.mytextandspeech;

public class ListenAndSpeakModal {
    public ListenAndSpeakModal(int id, String templateName, String templateDescription) {
        this.id = id;
        this.templateName = templateName;
        this.templateDescription = templateDescription;
    }

    int id;

    public ListenAndSpeakModal() {
    }

    String templateName;
    String templateDescription;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Override
    public String toString() {
        return "ListenAndSpeakModal{" +
                "id=" + id +
                ", templateName='" + templateName + '\'' +
                ", templateDescription='" + templateDescription + '\'' +
                '}';
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }




}
