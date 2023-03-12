package com.example.cocomarket.Controller;


import com.example.cocomarket.Entity.*;
import com.example.cocomarket.Interfaces.*;
import com.example.cocomarket.Repository.Produit__Repository;
import com.example.cocomarket.Services.ContratPDFExport;
import com.example.cocomarket.Services.Contrat_Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class SnoussiController {

    @Autowired

    IShop shopinterface;

    @Autowired
    ICategorie categorieinterface;

    @Autowired
    IProduit produitinterface;

    @Autowired
    IRainting_Product raitingprointerface;

    @Autowired
    IContrat contratinterface ;


    @GetMapping("shop")
    @PreAuthorize("hasAuthority('Zebi')")
    public List<Shop> AfficherLesShop() {
        return shopinterface.AfficherLesShop();
    }

    @PostMapping("/add-shop")
    public Shop AddNewshop(@RequestBody Shop shp) {

        Shop shop = shopinterface.AddNewshop(shp);
        return shop;
    }

    @PostMapping("/add-Categorie")
    public Categorie AddnewCategorie(@RequestBody Categorie cat) {
        categorieinterface.AddSubnewCategorie(cat);
        Categorie categorie = categorieinterface.AddnewCategorie(cat);
        return categorie;
    }


    @DeleteMapping("/supprimershop/{idShop}")
    void supprimerShop(@PathVariable("idShop") int id) {
        shopinterface.supprimerShop(id);

    }


    @PostMapping("/ajouterProduit/{idShop}/{idCateg}")
    public void AddProduitAffeASHopAndAffeAcategorie(@RequestBody Produit produit, @PathVariable Integer idShop, @PathVariable Integer idCateg) {
        produitinterface.AddnewProduit(produit);
        produitinterface.AddProduitAffeASHopAndAffeAcategorie(produit.getId(), idShop, idCateg);
    }

    @PostMapping("/ajouterRainting/{idProduit}")
    public void AddRaitingtoProduit(@RequestBody Raiting_Product raintingP, @PathVariable Integer idProduit) {
        System.out.println("--------I'm IN Function--------");
        raitingprointerface.AddNEwRaitingProduit(raintingP);
        produitinterface.AddRaitingtoProduit(raintingP.getId(),idProduit);


    }


    private final Produit__Repository produit__repository;

    public SnoussiController(Produit__Repository produit__repository) {
        this.produit__repository = produit__repository;
    }

 /*   @GetMapping("/recherche")
    public ResponseEntity<List<Produit>> searchForProduit(@SearchSpec Specification<Produit> specs) {
        return new ResponseEntity<>(produit__repository.findAll(Specification.where(specs)), HttpStatus.OK);
    }
*/
    @GetMapping(path = "/getqrcode", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage generateQRCodeImage(@RequestParam String url) throws Exception {
//QRcode generator logic
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 250, 250);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @GetMapping("/SumRaint")
    public String sumProduit (Integer idproduit,  Integer idporduit2 )

    {
        return produitinterface.SumRatting(idproduit,idporduit2) ;
    }

    @PostMapping("/Ajoutercontrat/{idshop}")
    public void AddContratToShop(@RequestBody Contrat contrat, @PathVariable Integer idshop) {
        System.out.println("--------I'm IN Function--------");
        contratinterface.AddContratEtAffecter(contrat);
        shopinterface.AddContratToShop(contrat.getId(),idshop);

    }

  @GetMapping("/AffichageListeProduit")
  public List<Produit> Recomendation(Integer idproduit , Integer idCateg){
      return produitinterface.Recomendation(idproduit , idCateg);

  }
    @Autowired
    private Contrat_Service service;

    @GetMapping("/users/export/pdf")
    @PreAuthorize("hasAuthority('Zebi')")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Contrat> listUsers = service.listAll();

        ContratPDFExport exporter = new ContratPDFExport(listUsers);
        exporter.export(response);

    }
}







