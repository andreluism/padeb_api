/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Municipio;
import Model.Regiao;
import Model.UF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.swing.event.ChangeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 *
 * @author Andre
 */
@ManagedBean(name = "filtroController")
@SessionScoped
public class FiltroController implements Serializable {

    private CartesianChartModel categoryModel;
    private int serie;
    private int tipoRede;
    private int localizacao;
    private int capital;
    private int dependenciaAdm;
    private List<Regiao> regioesDatatable;
    private List<UF> UFsDatatable;
    private List<Municipio> municipiosDatatable;
    private List<Regiao> regioesChart;
    private List<UF> UFsChart;
    private List<Municipio> municipiosChart;
    private List<Municipio> municipiosDB;
    private List<UF> UFsDB;
    private List<Regiao> regioesDB;
    private DualListModel<Municipio> municipios;
    private DualListModel<UF> UFs;
    private DualListModel<Regiao> regioes;

    public FiltroController() {
    }
    
    @PostConstruct
    public void inicializa() {
        createCategoryModel();
        createRegioes();
        createUFs();
        createMunicipios();
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }
    
    public void handleSelectionRegiao(TransferEvent  e) {
        List<Regiao> targetRegiao = (List<Regiao>) e.getItems();
        List<Long> a = new ArrayList<Long>();
        List<Long> b = new ArrayList<Long>();
        List<Regiao> regioesCopy = new ArrayList<Regiao>();
        if (e.isAdd() == true) {
            regioes.getTarget().addAll(targetRegiao);
            
        } else {
            for (Regiao _regiao : regioes.getTarget()) {
                a.add(_regiao.getRegiao());
            }
            for (Regiao _regiao : (List<Regiao>) e.getItems()) {
                b.add(_regiao.getRegiao());
            }
            a.removeAll(b);
            for (Regiao _regiao : regioes.getTarget()) {
                if (a.contains(_regiao.getRegiao())) {
                    regioesCopy.add(_regiao);
                }                                    
            }
            regioes.setTarget(regioesCopy);
        }
        atualizarUFs();        
    }
    
    public void handleSelectionUF(TransferEvent  e) {
        List<UF> targetUF = (List<UF>) e.getItems();
        List<Long> a = new ArrayList<Long>();
        List<Long> b = new ArrayList<Long>();
        List<UF> UFsCopy = new ArrayList<UF>();
        if (e.isAdd() == true) {
            UFs.getTarget().addAll(targetUF);
            
        } else {
            for (UF _UF : UFs.getTarget()) {
                a.add(_UF.getUf());
            }
            for (UF _UF : (List<UF>) e.getItems()) {
                b.add(_UF.getUf());
            }
            a.removeAll(b);
            for (UF _UF : UFs.getTarget()) {
                if (a.contains(_UF.getUf())) {
                    UFsCopy.add(_UF);
                }                                    
            }
            UFs.setTarget(UFsCopy);
        }
        atualizarMunicipios();        
    }
    
    public void handleSelectionMunicipio(TransferEvent  e) {
        List<Municipio> targetMunicipio = (List<Municipio>) e.getItems();
        List<Long> a = new ArrayList<Long>();
        List<Long> b = new ArrayList<Long>();
        List<Municipio> municipiosCopy = new ArrayList<Municipio>();
        if (e.isAdd() == true) {
            municipios.getTarget().addAll(targetMunicipio);
            
        } else {
            for (Municipio _municipio : municipios.getTarget()) {
                a.add(_municipio.getMunicipio());
            }
            for (Municipio _municipio : (List<Municipio>) e.getItems()) {
                b.add(_municipio.getUf());
            }
            a.removeAll(b);
            for (Municipio _municipio : municipios.getTarget()) {
                if (a.contains(_municipio.getMunicipio())) {
                    municipiosCopy.add(_municipio);
                }                                    
            }
            municipios.setTarget(municipiosCopy);
        }
    }
    
    public List<Regiao> convertLHMToRegiao(List<LinkedHashMap> linkedHM) {
        List<Regiao> _regioes = new ArrayList<Regiao>();
        for (LinkedHashMap l : linkedHM) {
            long _id = Long.valueOf((Integer) l.get("id"));
            long _regiao = Long.valueOf((Integer) l.get("regiao"));            
            float _mediaLP = Float.parseFloat((l.get("mediaLP")).toString());
            float _mediaMT = Float.parseFloat((l.get("mediaMT")).toString());
            float _desvioPadraoLP = Float.parseFloat((l.get("desvioPadraoLP")).toString());
            float _desvioPadraoMT = Float.parseFloat((l.get("desvioPadraoMT")).toString());
            float _nivel0LP = Float.parseFloat((l.get("nivel0LP")).toString());
            float _nivel1LP = Float.parseFloat((l.get("nivel1LP")).toString());
            float _nivel2LP = Float.parseFloat((l.get("nivel2LP")).toString());
            float _nivel3LP = Float.parseFloat((l.get("nivel3LP")).toString());
            float _nivel4LP = Float.parseFloat((l.get("nivel4LP")).toString());
            float _nivel5LP = Float.parseFloat((l.get("nivel5LP")).toString());
            float _nivel6LP = Float.parseFloat((l.get("nivel6LP")).toString());
            float _nivel7LP = Float.parseFloat((l.get("nivel7LP")).toString());
            float _nivel8LP = Float.parseFloat((l.get("nivel8LP")).toString());
            float _nivel9LP = Float.parseFloat((l.get("nivel9LP")).toString());
            float _nivel10LP = Float.parseFloat((l.get("nivel10LP")).toString());
            float _nivel11LP = Float.parseFloat((l.get("nivel11LP")).toString());
            float _nivel0MT = Float.parseFloat((l.get("nivel0MT")).toString());
            float _nivel1MT = Float.parseFloat((l.get("nivel1MT")).toString());
            float _nivel2MT = Float.parseFloat((l.get("nivel2MT")).toString());
            float _nivel3MT = Float.parseFloat((l.get("nivel3MT")).toString());
            float _nivel4MT = Float.parseFloat((l.get("nivel4MT")).toString());
            float _nivel5MT = Float.parseFloat((l.get("nivel5MT")).toString());
            float _nivel6MT = Float.parseFloat((l.get("nivel6MT")).toString());
            float _nivel7MT = Float.parseFloat((l.get("nivel7MT")).toString());
            float _nivel8MT = Float.parseFloat((l.get("nivel8MT")).toString());
            float _nivel9MT = Float.parseFloat((l.get("nivel9MT")).toString());
            float _nivel10MT = Float.parseFloat((l.get("nivel10MT")).toString());
            float _nivel11MT = Float.parseFloat((l.get("nivel11MT")).toString());
            
            _regioes.add(new Regiao(_id,_regiao,(String) l.get("nome"), (Integer) l.get("serie"),(Integer) l.get("tipoRede"),(Integer) l.get("localizacao"),(Integer) l.get("capital"),(Integer) l.get("alunosPresentes"), _mediaLP, _mediaMT, _desvioPadraoLP, _desvioPadraoMT, _nivel0LP, _nivel1LP, _nivel2LP, _nivel3LP, _nivel4LP, _nivel5LP, _nivel6LP, _nivel7LP, _nivel8LP, _nivel9LP, _nivel10LP, _nivel11LP, _nivel0MT, _nivel1MT, _nivel2MT, _nivel3MT, _nivel4MT, _nivel5MT, _nivel6MT, _nivel7MT, _nivel8MT, _nivel9MT, _nivel10MT, _nivel11MT));
        }
        return _regioes;
    }
    
    public List<UF> convertLHMToUF(List<LinkedHashMap> linkedHM) {
        List<UF> _ufs = new ArrayList<UF>();
        for (LinkedHashMap l : linkedHM) {
            long _id = Long.valueOf((Integer) l.get("id"));
            long _uf = Long.valueOf((Integer) l.get("uf"));
            long _regiao = Long.valueOf((Integer) l.get("regiao"));            
            float _mediaLP = Float.parseFloat((l.get("mediaLP")).toString());
            float _mediaMT = Float.parseFloat((l.get("mediaMT")).toString());
            float _desvioPadraoLP = Float.parseFloat((l.get("desvioPadraoLP")).toString());
            float _desvioPadraoMT = Float.parseFloat((l.get("desvioPadraoMT")).toString());
            float _nivel0LP = Float.parseFloat((l.get("nivel0LP")).toString());
            float _nivel1LP = Float.parseFloat((l.get("nivel1LP")).toString());
            float _nivel2LP = Float.parseFloat((l.get("nivel2LP")).toString());
            float _nivel3LP = Float.parseFloat((l.get("nivel3LP")).toString());
            float _nivel4LP = Float.parseFloat((l.get("nivel4LP")).toString());
            float _nivel5LP = Float.parseFloat((l.get("nivel5LP")).toString());
            float _nivel6LP = Float.parseFloat((l.get("nivel6LP")).toString());
            float _nivel7LP = Float.parseFloat((l.get("nivel7LP")).toString());
            float _nivel8LP = Float.parseFloat((l.get("nivel8LP")).toString());
            float _nivel9LP = Float.parseFloat((l.get("nivel9LP")).toString());
            float _nivel10LP = Float.parseFloat((l.get("nivel10LP")).toString());
            float _nivel11LP = Float.parseFloat((l.get("nivel11LP")).toString());
            float _nivel0MT = Float.parseFloat((l.get("nivel0MT")).toString());
            float _nivel1MT = Float.parseFloat((l.get("nivel1MT")).toString());
            float _nivel2MT = Float.parseFloat((l.get("nivel2MT")).toString());
            float _nivel3MT = Float.parseFloat((l.get("nivel3MT")).toString());
            float _nivel4MT = Float.parseFloat((l.get("nivel4MT")).toString());
            float _nivel5MT = Float.parseFloat((l.get("nivel5MT")).toString());
            float _nivel6MT = Float.parseFloat((l.get("nivel6MT")).toString());
            float _nivel7MT = Float.parseFloat((l.get("nivel7MT")).toString());
            float _nivel8MT = Float.parseFloat((l.get("nivel8MT")).toString());
            float _nivel9MT = Float.parseFloat((l.get("nivel9MT")).toString());
            float _nivel10MT = Float.parseFloat((l.get("nivel10MT")).toString());
            float _nivel11MT = Float.parseFloat((l.get("nivel11MT")).toString());
            
            _ufs.add(new UF(_id, _uf, _regiao,(String) l.get("nome"),(Integer) l.get("serie"),(Integer) l.get("tipoRede"),(Integer) l.get("localizacao"),(Integer) l.get("capital"),(Integer) l.get("alunosPresentes"), _mediaLP, _mediaMT, _desvioPadraoLP, _desvioPadraoMT, _nivel0LP, _nivel1LP, _nivel2LP, _nivel3LP, _nivel4LP, _nivel5LP, _nivel6LP, _nivel7LP, _nivel8LP, _nivel9LP, _nivel10LP, _nivel11LP, _nivel0MT, _nivel1MT, _nivel2MT, _nivel3MT, _nivel4MT, _nivel5MT, _nivel6MT, _nivel7MT, _nivel8MT, _nivel9MT, _nivel10MT, _nivel11MT));
        }
        return _ufs;
    }
    
    public List<Municipio> convertLHMToMunicipio(List<LinkedHashMap> linkedHM) {
        List<Municipio> _municipios = new ArrayList<Municipio>();
        for (LinkedHashMap l : linkedHM) {
            long _id = Long.valueOf((Integer) l.get("id"));
            long _municipio = Long.valueOf((Integer) l.get("municipio"));
            long _uf = Long.valueOf((Integer) l.get("uf"));
            long _regiao = Long.valueOf((Integer) l.get("regiao"));            
            float _mediaLP = Float.parseFloat((l.get("mediaLP")).toString());
            float _mediaMT = Float.parseFloat((l.get("mediaMT")).toString());
            float _somaPesos = Float.parseFloat((l.get("somaPesos")).toString());
            float _taxaParticipacao = Float.parseFloat((l.get("taxaParticipacao")).toString());
            float _nivel0LP = Float.parseFloat((l.get("nivel0LP")).toString());
            float _nivel1LP = Float.parseFloat((l.get("nivel1LP")).toString());
            float _nivel2LP = Float.parseFloat((l.get("nivel2LP")).toString());
            float _nivel3LP = Float.parseFloat((l.get("nivel3LP")).toString());
            float _nivel4LP = Float.parseFloat((l.get("nivel4LP")).toString());
            float _nivel5LP = Float.parseFloat((l.get("nivel5LP")).toString());
            float _nivel6LP = Float.parseFloat((l.get("nivel6LP")).toString());
            float _nivel7LP = Float.parseFloat((l.get("nivel7LP")).toString());
            float _nivel8LP = Float.parseFloat((l.get("nivel8LP")).toString());
            float _nivel9LP = Float.parseFloat((l.get("nivel9LP")).toString());
            float _nivel10LP = Float.parseFloat((l.get("nivel10LP")).toString());
            float _nivel11LP = Float.parseFloat((l.get("nivel11LP")).toString());
            float _nivel12LP = Float.parseFloat((l.get("nivel12LP")).toString());
            float _nivel13LP = Float.parseFloat((l.get("nivel13LP")).toString());
            float _nivel14LP = Float.parseFloat((l.get("nivel14LP")).toString());
            float _nivel15LP = Float.parseFloat((l.get("nivel15LP")).toString());
            float _nivel0MT = Float.parseFloat((l.get("nivel0MT")).toString());
            float _nivel1MT = Float.parseFloat((l.get("nivel1MT")).toString());
            float _nivel2MT = Float.parseFloat((l.get("nivel2MT")).toString());
            float _nivel3MT = Float.parseFloat((l.get("nivel3MT")).toString());
            float _nivel4MT = Float.parseFloat((l.get("nivel4MT")).toString());
            float _nivel5MT = Float.parseFloat((l.get("nivel5MT")).toString());
            float _nivel6MT = Float.parseFloat((l.get("nivel6MT")).toString());
            float _nivel7MT = Float.parseFloat((l.get("nivel7MT")).toString());
            float _nivel8MT = Float.parseFloat((l.get("nivel8MT")).toString());
            float _nivel9MT = Float.parseFloat((l.get("nivel9MT")).toString());
            float _nivel10MT = Float.parseFloat((l.get("nivel10MT")).toString());
            float _nivel11MT = Float.parseFloat((l.get("nivel11MT")).toString());
            float _nivel12MT = Float.parseFloat((l.get("nivel12MT")).toString());
            float _nivel13MT = Float.parseFloat((l.get("nivel13MT")).toString());
            float _nivel14MT = Float.parseFloat((l.get("nivel14MT")).toString());
            float _nivel15MT = Float.parseFloat((l.get("nivel15MT")).toString());

            
            
            _municipios.add(new Municipio(_id, _municipio, _uf, _regiao,(String) l.get("nome"),(Integer) l.get("serie"),(Integer) l.get("tipoRede"),(Integer) l.get("localizacao"),(Integer) l.get("alunosPresentes"), _mediaLP, _mediaMT, _somaPesos, _taxaParticipacao, _nivel0LP, _nivel1LP, _nivel2LP, _nivel3LP, _nivel4LP, _nivel5LP, _nivel6LP, _nivel7LP, _nivel8LP, _nivel9LP, _nivel10LP, _nivel11LP, _nivel12LP, _nivel13LP, _nivel14LP, _nivel15LP, _nivel0MT, _nivel1MT, _nivel2MT, _nivel3MT, _nivel4MT, _nivel5MT, _nivel6MT, _nivel7MT, _nivel8MT, _nivel9MT, _nivel10MT, _nivel11MT, _nivel12MT, _nivel13MT, _nivel14MT, _nivel15MT));
        }
        return _municipios;
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

        ChartSeries mediaLP = new ChartSeries();
        mediaLP.setLabel("Média Lingua Portuguesa");
        ChartSeries mediaMT = new ChartSeries();
        mediaMT.setLabel("Média Matemática");
        if (regioesChart != null) {
            for (Regiao _regiao : regioesChart) {
                mediaLP.set(_regiao.getNome(), _regiao.getMediaLP());
                mediaMT.set(_regiao.getNome(), _regiao.getMediaMT());
            }
        }
        if (UFsChart != null) {
            for (UF _uf : UFsChart) {
                mediaLP.set(_uf.getNome(), _uf.getMediaLP());
                mediaMT.set(_uf.getNome(), _uf.getMediaMT());
            }
        }
        if (municipiosChart != null) {
            for (Municipio _municipio : municipiosChart) {
                mediaLP.set(_municipio.getNome(), _municipio.getMediaLP());
                mediaMT.set(_municipio.getNome(), _municipio.getMediaMT());
            }
        }

        categoryModel.addSeries(mediaLP);
        categoryModel.addSeries(mediaMT);
    }


    private void atualizarUFs() {
        List<UF> UFsSource = new ArrayList<UF>();
        List<UF> UFsTarget = new ArrayList<UF>();
        regioes.getTarget();
        for (Regiao _regiao : regioes.getTarget()) {
            for (UF _uf : UFsDB) {
                if (_uf.getRegiao() == _regiao.getRegiao()) {
                    UFsSource.add(_uf);
                }
            }
        }
        for (Regiao _regiao : regioes.getTarget()) {
            for (UF _uf : UFs.getTarget()) {
                if (_uf.getRegiao() == _regiao.getRegiao()) {
                    UFsTarget.add(_uf);
                }
            }
        }
        UFs.setSource(UFsSource);
        UFs.setTarget(UFsTarget);
        atualizarMunicipios();
    }
    
    private void atualizarMunicipios() {
        List<Municipio> municipiosSource = new ArrayList<Municipio>();
        List<Municipio> municipiosTarget = new ArrayList<Municipio>();
        UFs.getTarget();
        for (UF _UFs : UFs.getTarget()) {
            for (Municipio _municipio : municipiosDB) {
                if (_municipio.getUf()== _UFs.getUf()) {
                    municipiosSource.add(_municipio);
                }
            }
        }
        for (UF _UFs : UFs.getTarget()) {
            for (Municipio _municipio : municipios.getTarget()) {
                if (_municipio.getUf()== _UFs.getUf()) {
                    municipiosTarget.add(_municipio);
                }
            }
        }
        municipios.setSource(municipiosSource);
        municipios.setTarget(municipiosTarget);
    }

    public DualListModel<Regiao> getRegioes() {
        return regioes;
    }

    public void setRegioes(DualListModel<Regiao> regioes) {
        this.regioes = regioes;
    }

    private void createRegioes() {
        if (regioesDB == null) {
            regioesDB = convertLHMToRegiao((List) new Utils.test().getRegioes());
        }
        if (regioes == null) {
            regioes = new DualListModel<Regiao>(regioesDB, new ArrayList<Regiao>());
        }
    }

    public List<UF> getUFsDB() {
        return UFsDB;
    }

    public void setUFsDB(List<UF> UFsDB) {
        this.UFsDB = UFsDB;
    }

    public List<Regiao> getRegioesDB() {
        return regioesDB;
    }

    public void setRegioesDB(List<Regiao> regioesDB) {
        this.regioesDB = regioesDB;
    }

    public DualListModel<UF> getUFs() {
        return UFs;
    }

    public void setUFs(DualListModel<UF> UFs) {
        this.UFs = UFs;
    }

    private void createUFs() {
        if (UFsDB == null) {
            UFsDB = convertLHMToUF((List) new Utils.test().getUFs());
        }
        if (UFs == null) {
            UFs = new DualListModel<UF>(new ArrayList<UF>(), new ArrayList<UF>());
        }
    }
    
    private void createMunicipios() {
        if (municipiosDB == null) {
            municipiosDB = convertLHMToMunicipio((List) new Utils.test().getMunicipios());
        }
        if (municipios == null) {
            municipios = new DualListModel<Municipio>(new ArrayList<Municipio>(), new ArrayList<Municipio>());
        }                       
    }

    public List<Municipio> getMunicipiosDB() {
        return municipiosDB;
    }

    public void setMunicipiosDB(List<Municipio> municipiosDB) {
        this.municipiosDB = municipiosDB;
    }

    public DualListModel<Municipio> getMunicipios() {
        return municipios;
    }

    public void setMunicipios(DualListModel<Municipio> municipios) {
        this.municipios = municipios;
    }
    
    public boolean checarRenderizacao(List pickListsTarget) {
        try {
           pickListsTarget.get(0);
           return true; 
        } catch (Exception e) {
        } return false;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getTipoRede() {
        return tipoRede;
    }

    public void setTipoRede(int tipoRede) {
        this.tipoRede = tipoRede;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getDependenciaAdm() {
        return dependenciaAdm;
    }

    public void setDependenciaAdm(int dependenciaAdm) {
        this.dependenciaAdm = dependenciaAdm;
    }
    
    public void handleSerie(ValueChangeEvent e) {
        serie = (Integer) e.getNewValue();
    }
    
    public void handleTipoRede(ValueChangeEvent e) {
        tipoRede = (Integer) e.getNewValue();
    }
    
    public void handleLocalizacao(ValueChangeEvent e) {
        localizacao = (Integer) e.getNewValue();
    }
    
    public void handleCapital(ValueChangeEvent e) {
        capital = (Integer) e.getNewValue();
    }
    
    public void prepareForChart() {
        regioesChart = convertLHMToRegiao((List) new Utils.test().getRegioesForChart(regioesDatatable, serie, tipoRede, localizacao, capital));
        UFsChart = convertLHMToUF((List) new Utils.test().getUFsForChart(UFsDatatable, regioesDatatable, serie, tipoRede, localizacao, capital));
        municipiosChart = convertLHMToMunicipio((List) new Utils.test().getMunicipiosForChart(municipiosDatatable, UFsDatatable, regioesDatatable, serie, tipoRede, localizacao));
    }

    public List<Regiao> getRegioesChart() {
        return regioesChart;
    }

    public void setRegioesChart(List<Regiao> regioesChart) {
        this.regioesChart = regioesChart;
    }

    public List<Regiao> getRegioesDatatable() {
        return regioesDatatable;
    }

    public void setRegioesDatatable(List<Regiao> regioesDatatable) {
        this.regioesDatatable = regioesDatatable;
    }

    public List<UF> getUFsDatatable() {
        return UFsDatatable;
    }

    public void setUFsDatatable(List<UF> UFsDatatable) {
        this.UFsDatatable = UFsDatatable;
    }

    public List<Municipio> getMunicipiosDatatable() {
        return municipiosDatatable;
    }

    public void setMunicipiosDatatable(List<Municipio> municipiosDatatable) {
        this.municipiosDatatable = municipiosDatatable;
    }

    public List<UF> getUFsChart() {
        return UFsChart;
    }

    public void setUFsChart(List<UF> UFsChart) {
        this.UFsChart = UFsChart;
    }

    public List<Municipio> getMunicipiosChart() {
        return municipiosChart;
    }

    public void setMunicipiosChart(List<Municipio> municipiosChart) {
        this.municipiosChart = municipiosChart;
    }
    
    public String getDatatipFormat(){
        return "<span style=\"display:none;\">%s</span><span>%s</span>";
    }
}
