package edu.pe.cibertec.grupo2.DAWI_CL1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.time.Period;

@Controller
@RequestMapping("/ejercicio")
public class EjercicioController {

    @GetMapping("/uno")
    public String ejercicioEstacionamiento(){
        return "ejercicio/frmestacionamiento";
    }
    @PostMapping("/uno")
    public String ejercicioEstacionamiento(@RequestParam("horas") int horas, @RequestParam("minutos") int minutos,
                                           Model model){

        double total;

        total = horas * 1.5 + (minutos * (1.5/60));

        model.addAttribute("horas", horas);
        model.addAttribute("minutos", minutos);
        model.addAttribute("total", total);

        return "ejercicio/frmestacionamiento";
    }
    @GetMapping("/dos")
    public String ejercicioPromedio(){
        return "ejercicio/frmnotasalumno";
    }

    @PostMapping("/dos")
    public String ejercicioPromedio(@RequestParam("n1") int n1, @RequestParam("n2") int n2,
                                    @RequestParam("n3") int n3, @RequestParam("n4") int n4,
                                    Model model){
        double promedio;
        String nEliminada;

        if(n1 <= n2 && n1 <= n3 && n1 <= n4) {
            promedio = n2 * 0.2 + n3 * 0.3 + n4 * 0.5;
            nEliminada = n1 + " (Nota 1)";
        } else if(n2 <= n1 && n2 <= n3 && n2 <= n4) {
            promedio = n1 * 0.2 + n3 * 0.3 + n4 * 0.5;
            nEliminada = n2 + " (Nota 2)";
        } else if(n3 <= n1 && n3 <= n2 && n3 <= n4) {
            promedio = n1 * 0.2 + n2 * 0.3 + n4 * 0.5;
            nEliminada = n3 + " (Nota 3)";
        } else {
            promedio = n1 * 0.2 + n2 * 0.3 + n3 * 0.5;
            nEliminada = n4 + " (Nota 4)";
        }

        model.addAttribute("nEliminada", nEliminada);
        model.addAttribute("promedio", promedio);
        model.addAttribute("notas", "Nota 1: " + n1 +
                ", Nota 2: " + n2 + ", Nota 3: " + n3 + ", Nota 4: " + n4);

        return "ejercicio/frmnotasalumno";
    }

    @GetMapping("/tres")
    public String ejercicioDNI(){
        return "ejercicio/frmverificaciondni";
    }
    @PostMapping("/tres")
    public String ejercicioDNI(@RequestParam("fechaNacimiento") String fechaNacimiento, Model model) {
        String mensaje;

        LocalDate fechaNacimientoDate = LocalDate.parse(fechaNacimiento);
        Period edad = Period.between(fechaNacimientoDate, LocalDate.now());
        //SEGUN LA RENIEC LA EDAD PARA HACER EL TRAMITE DE DNI AZUL ES A LOS 17 AÑOS
        if (edad.getYears() >= 17) {
            mensaje = "¡Debe acercarse a sacar su DNI Azul!";
        } else {
            int aniosRestantes = 17 - edad.getYears();
            mensaje = "Aún no tienes la edad suficiente. Deberás sacar tu DNI Azul dentro de " + aniosRestantes + " años.";
        }
        model.addAttribute("mensaje", mensaje);
        return "ejercicio/frmverificaciondni";
    }

    @GetMapping("/cuatro")
    public String ejercicioNumerosPares(){
        return "ejercicio/frmnumerosparesdesc";
    }
}
