package santhosh.pm.ormorange.orangeUtil;

class MigrationFileParser {
    private String content;

    /**
     * @param content
     */
    public MigrationFileParser(String content){
        this.content = content.replaceAll("(\\/\\*([\\s\\S]*?)\\*\\/)|(--(.)*)|(\n)","");
    }

    public String[] getStatements(){
        return this.content.split(";");
    }
}
