/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onzebuurt;

import java.sql.*;

/**
 *
 * @author Stef
 */
public class DBCconnectie {
    
    /*
     * Dit voorbeeld maakt gebruik van een MySQL databank met naam ProductDB die draait op localhost.
     * De MySQL driver voor JDBC is toegevoegd aan de libraries voor dit project.
     * 
     * Het voorbeeld kan werken met eender welke databank, door onderstaande URL aan te passen of een van de alternatieve URLs te gebruiken.
     * Uiteraard moet de tabel PRODUCT (zie hierboven) steeds beschikbaar zijn.
     * Ook de bijhorende driver moet aan het project toegevoegd worden.
     * De JDBC drivers voor MySQL, Derby en Microsoft SQL Server zijn te vinden in de map 'drivers' in de projectfolder.
     */
    private static final String JDBC_URL_MYSQL = "jdbc:mysql://localhost:3306/OnzeBuurt?user=root&password=Godjen01";
    
    
    
    public static void main(String[] args) {
           System.out.print("Boobs");
        // De eerste stap is het opzetten van een connectie met de databank via de methode DriverManager.getConnection().
        // We geven hierbij een correcte JDBC URL op.
        // Merk op dat we het Connection object aanmaken binnen een zogenaamde try-with-resources (Java 7).
        // Dit zal ervoor zorgen dat de connectie steeds wordt afgesloten (zoals bij een try/finally).
        try (Connection conn = DriverManager.getConnection(JDBC_URL_MYSQL)) {
            
            // Om SQL queries te kunnen uitvoeren, vragen we een Statement object aan de Connection.
            // Dit Statement kan allerlei queries uitvoeren en is bovendien herbruikbaar.
            Statement stat = conn.createStatement();

            // We gebruiken het Statement om 2 rijen toe te voegen aan de tabel PRODUCT.
            // INSERT, UPDATE en DELETE queries voer je uit met de methode executeUpdate().
            // De returnwaarde van deze methode is het aantal aangepaste rijen, maar deze waarde hoef je niet op te slaan.
            /*stat.executeUpdate("INSERT INTO Event VALUES(1, 'Een stoel met 4 poten', ' ')");
            stat.executeUpdate("INSERT INTO Event VALUES(2, 'Een hypermoderne stoel met 3 poten', ' ')");
            System.out.println("insert gebeurt");*/
            

            // Tenslotte verwijderen we de 2 producten zodat de databank terug in de begintoestand is.
            stat.executeUpdate("DELETE FROM Event WHERE idEvent = 1 OR idEvent = 2");

            // Ook een Statement sluit je best zo snel mogelijk af. We maken hierbij dezelfde opmerkingen als bij het afsluiten van de ResultSet.
            // Een Connection zal bij het afsluiten ook alle openstaande Statements en ResultSets afsluiten, maar bij voorkeur sluit je deze vroeger af.
            stat.close();
            
        // Bij het werken met een databank kunnen heel wat SQL exceptions voorkomen.
        // We voorzien een catch blok om deze op te vangen.
        // Omdat SQL exceptions als ketting kunnen voorkomen, voorzien we een for lus die alle exceptions in de ketting zal overlopen en afdrukken.
        } catch (SQLException ex) {
            for (Throwable t : ex) {
                t.printStackTrace();
                System.out.println("failed");
            }
        }
    }
}
