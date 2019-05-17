/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Compartir.Informes;
import Conexion.DBConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author manue
 */
public class InformesOP {
    
    DBConnection conn = new DBConnection();
    
    public String GenerarInforme(Informes informes) {
        byte[] pdf = "".getBytes();
        String pdf_encoded = "";
        try {
            JasperReport informe = (JasperReport) JRLoader.loadObject(informes.getTipo()+".jasper");
            Map<String, Integer> parametros = new HashMap<String, Integer>();
            parametros.put("ID_Usuario", informes.getId_usuario());
            
            Connection conexion = conn.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(informe, parametros, conexion);

            File pdf_ruta = new File("reports/report"+"_"+"tipo.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf_ruta));

            Path pdfPath = Paths.get("reports/report"+"_"+"tipo.pdf");
            pdf = Files.readAllBytes(pdfPath);
            pdf_encoded = Base64.getEncoder().encodeToString(pdf);
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InformesOP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InformesOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdf_encoded;
    }
    
}
