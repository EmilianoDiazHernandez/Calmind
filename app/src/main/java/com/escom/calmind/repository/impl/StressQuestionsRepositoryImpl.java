package com.escom.calmind.repository.impl;

import com.escom.calmind.repository.StressQuestionsRepository;

import javax.inject.Singleton;

@Singleton
public class StressQuestionsRepositoryImpl implements StressQuestionsRepository {

    @Override
    public String[] getAll() {
        return new String[]{
                "¿Con qué frecuencia ha estado afectado por algo que ha ocurrido inesperadamente?",
                "¿Con qué frecuencia se ha sentido incapaz de controlar las cosas importantes en su vida?",
                "¿Con qué frecuencia se ha sentido nervioso o estresado?",
                "¿Con qué frecuencia ha manejado con éxito los pequeños problemas irritantes de la vida?",
                "¿Con qué frecuencia ha sentido que ha afrontado efectivamente los cambios importantes que han estado ocurriendo en su vida?",
                "¿Con qué frecuencia ha estado seguro sobre su capacidad para manejar sus problemas personales?",
                "¿Con qué frecuencia ha sentido que las cosas le van bien?",
                "¿Con qué frecuencia ha sentido que no podía afrontar todas las cosas que tenía que hacer?",
                "¿Con qué frecuencia ha podido controlar las dificultades de su vida?",
                "¿Con que frecuencia se ha sentido que tenia todo bajo control?",
                "¿Con qué frecuencia ha estado enfadado porque  las cosas que le han ocurrido estaban fuera de su control?",
                "¿Con qué frecuencia ha  pensado sobre las cosas que le quedan  por hacer?",
                "¿Con qué frecuencia ha podido controlar la forma  de  pasar  el tiempo?",
                "¿Con qué frecuencia ha sentido que las dificultades se acumulan tanto que no puede superarlas?"};
    }
}
