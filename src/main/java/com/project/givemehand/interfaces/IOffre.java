package com.project.givemehand.interfaces;

import com.project.givemehand.models.entity.Filtre;
import com.project.givemehand.models.entity.Offre;

import java.util.List;

public interface IOffre {

    public List<Offre> filtrerOffre(Filtre f);
}
