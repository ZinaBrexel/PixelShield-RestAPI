package fr.simplon.pixelshielrestapi.controller;

import fr.simplon.pixelshielrestapi.entity.Survey;
import fr.simplon.pixelshielrestapi.repository.SurveyRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pixelshield")
public class SurveyController {

    private final SurveyRepository surveyRepository;
    private Survey survey;
    private BindingResult validation;
    private HttpServletRequest request;


    @GetMapping(path = "/sondages")
    @ApiResponse(responseCode = "200", description = "La ressource a été trouvée et renvoyée avec succès.")
    public Collection<Survey>surveys(){
        return surveyRepository.findAll();
    }

    @GetMapping(path = "/sondages/{id}")
    @ApiResponse(responseCode = "200", description = "La ressource a été trouvée et renvoyée avec succès.")
    @ApiResponse(responseCode = "404", description = "La ressource n'existe pas.")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id){
        return ResponseEntity.of(surveyRepository.findById(id));
    }

    @PostMapping( "/sondages")
    @ApiResponse(responseCode = "201", description = "La ressource a été créée avec succès.")
    @ApiResponse(responseCode = "400", description = "En cas d'erreur de validation.")
    public ResponseEntity<?> addSurvey(
            @Valid @RequestBody Survey survey, BindingResult validation, HttpServletRequest request) {
      if(validation.hasErrors()){
          Collection<String> errors = validation.getAllErrors().stream()
                  .map(DefaultMessageSourceResolvable::getDefaultMessage)
                  .collect(Collectors.toList());
          return ResponseEntity.badRequest().body(errors);
      }
      URI location = ServletUriComponentsBuilder.fromRequest(request)
              .path("/{id}")
              .buildAndExpand(survey.getId())
              .toUri();
      return ResponseEntity.created(location).body(survey);
    }

    @PutMapping("/sondages/{id}")
    @ApiResponse(responseCode = "200", description = "La ressource a été mise à jour avec succès.")
    @ApiResponse(responseCode = "404", description = "La ressource à mettre à jour n'a pas été trouvée.")
    public ResponseEntity<?> updateSurvey(
            @PathVariable Long id,
            @RequestBody @Valid Survey survey,
            BindingResult validation,
            HttpServletRequest request){
        if(validation.hasErrors()){
            Collection<String> errors = validation.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Survey updatedSurvey =surveyRepository.findById(id).map(surveyUpdated -> {
        surveyUpdated.setDescription(survey.getDescription());
        surveyUpdated.setQuestion(survey.getQuestion());
        surveyUpdated.setCreation_date(survey.getCreation_date());
        surveyUpdated.setClose_date(survey.getClose_date());
        surveyUpdated.setEmployee(survey.getEmployee());
        return surveyRepository.save(surveyUpdated);
        }).orElseGet(()-> null);
        if(updatedSurvey == null){
            return ResponseEntity.notFound()
                    .location(ServletUriComponentsBuilder.fromRequest(request).build().toUri())
                    .build();
        }
        return ResponseEntity.ok(updatedSurvey);
    }

    @DeleteMapping("/sondages/{id}")
    @ApiResponse(responseCode = "200", description = "La ressource n'existe pas, requête ignorée.")
    @ApiResponse(responseCode = "204", description = "La ressource a été supprimée avec succès.")
    public ResponseEntity deleteSurvey(@PathVariable Long id){
        if(surveyRepository.existsById(id)){
            surveyRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().build();
    }
}
