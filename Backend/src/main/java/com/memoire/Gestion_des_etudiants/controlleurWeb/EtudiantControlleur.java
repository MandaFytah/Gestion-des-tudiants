package com.memoire.Gestion_des_etudiants.controlleurWeb;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memoire.Gestion_des_etudiants.entites.Etudiant;
//import com.memoire.Gestion_des_etudiants.repository.DAO.EtudiantRepositoryInterface;
import com.memoire.Gestion_des_etudiants.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/gestionEtudiant/etudiants")
public class EtudiantControlleur {
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping("/7")
    public int getCode(){
        System.out.println("Get Numbers...");
        int x= etudiantService.nbre();
        System.out.println(x);
        if(x==0){
            return 0;
        }
        else {
            return etudiantService.max();
        }


    }
    //afficher la liste des etudiants enregistrés dans la base des données
    @GetMapping("/details")
    //public String listDetaille(HttpServletRequest request){
        public Iterable<Etudiant> list(){//@ResponseBody permet de retourner ici la liste des etudiant sous forme Json
        //on peut remplacer @Controller par @Restcontroller et supprimer @ResponseBody
        System.out.println("Get all etudiants..");
        //List<Etudiant> etudiants= etudiantService.getAll();
        //request.setAttribute("lisitra",etudiants);
        return etudiantService.getAll();
    }

    //à modifier (same as "/details")
    @GetMapping
    //public String list(HttpServletRequest request){
        public Iterable<Etudiant> listBrief(){
        System.out.println("Quelques informations sur les etudiants..");
        //List<Etudiant> etudiants= etudiantService.getAll();
        //request.setAttribute("brefInfo",etudiants);
        return  etudiantService.getAll();
        //return "etudiant" ;
    }
    //rechercher un etudinant par leur Id
    @GetMapping("/{id}")//@PathVariable===>id=idDetaille
    public ResponseEntity<Etudiant> post(@PathVariable("id") float idDetaille){
        Optional<Etudiant> etudiant= etudiantService.findById(idDetaille);
        return  etudiant.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> requestMap) {
        try {
            return etudiantService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", "Il y a une erreur");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//sauvegarder un etudiant
@PostMapping("/sauvegarder")
public ResponseEntity<?> saveEtudiantWithImage(
        @RequestPart("etudiant") Etudiant etudiantJson,
        @RequestPart(value = "file", required = false) MultipartFile file) {
    try {


        if (file != null && !file.isEmpty()) {
            //pour traiter le multipartFile
            File tempFile=File.createTempFile("temp",null);
            file.transferTo(tempFile);
            byte[] fileContent=FileUtils.readFileToByteArray(tempFile);
            etudiantJson.setPhoto(fileContent);
            tempFile.delete();
        }
        System.out.println("etudiant"+etudiantJson);
        Etudiant savedEtudiant = etudiantService.save(etudiantJson);
        return ResponseEntity.ok(savedEtudiant);
    } catch (IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la sauvegarde de l'étudiant");
    }
}
    //Mettre a jour l'etudiant mapper par l'id=code , la classe AjoutEtudiant=Etudiant-id
    @PostMapping("/update/{code}")
    public Etudiant update(@PathVariable("code") float id, @RequestBody Etudiant etudiant){
       etudiant.setIdEtudiant(id);
       // System.out.println(etudiant);
        return etudiantService.update(etudiant);
    }
    //
    @DeleteMapping("/{id}")
    public void delete(@PathVariable float id ){
        etudiantService.delete(id);
    }

    // Gerer une image
    /*@PostMapping("/uploadImage/{id}")
    public ResponseEntity<String> uploadImage(@PathVariable("id") float id, @RequestParam("file") MultipartFile file) {
        try {
            // Convertir l'image en Base64
            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            // Récupérer l'étudiant par son ID
            Optional<Etudiant> etudiantOptional = etudiantService.findById(id);
            if (etudiantOptional.isPresent()) {
                Etudiant etudiant = etudiantOptional.get();
                // Mettre à jour le champ photo avec l'image encodée en Base64
                etudiant.setPhoto(base64Image);
                etudiantService.save(etudiant);
                return ResponseEntity.ok("Image uploadée avec succès");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'upload de l'image");
        }
    }*/
    //Get le photo
    @GetMapping("/getImage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        Optional<Etudiant> etudiantOptional = etudiantService.findById(id);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            if (etudiant.getPhoto() != null) {
                try {
                    byte[] imageBytes = Base64.getDecoder().decode(etudiant.getPhoto());
                    return ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_JPEG)
                            .body(imageBytes);
                } catch (IllegalArgumentException e) {
                    // Erreur de décodage Base64
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
