package com.abugauch.bootcamp.Persistence;

/**
 * Created by Usuario on 24/1/2017.
 */
import java.util.ArrayList;
import java.util.List;

public interface defaultDAO <TipoDTO> {
    public boolean create(TipoDTO a);
    public TipoDTO  read (Object key);
    public List<TipoDTO> readAll();
}
