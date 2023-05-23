package com.mycompany.salaoproject.models;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.mycompany.salaoproject.Tela_DashController;
import com.mycompany.salaoproject.DAO.AgendamentoDAO;
import com.mycompany.salaoproject.DAO.ComparecimentoDAO;

public class ComparecimentoHandler {

    private AgendamentoDAO agendamentoDAO;
    private ComparecimentoDAO comparecimentoDAO;

    public ComparecimentoHandler(AgendamentoDAO agendamentoDAO, ComparecimentoDAO comparecimentoDAO) {
        this.agendamentoDAO = agendamentoDAO;
        this.comparecimentoDAO = comparecimentoDAO;
    }

    public void verificarComparecimentos() throws SQLException {
        List<Agendamento> agendamentos = agendamentoDAO.obterTodosAgendamentos(null);
        LocalDate currentDate = LocalDate.now();
        
        try {
            for (Agendamento agendamento : agendamentos) {
                LocalDate dataAgendamento = agendamento.getDataAgendamento().toLocalDate();
                if (dataAgendamento.isEqual(currentDate) || dataAgendamento.isBefore(currentDate)) {
                    if (!comparecimentoDAO.existeComparecimento(agendamento)) {
                        Comparecimento comparecimento = new Comparecimento(agendamento.getEmailCliente(), agendamento.getDataAgendamento());
                        comparecimentoDAO.criarComparecimento(comparecimento);

                        double valorMovimentadoEntrada = 100.0; 
                        LocalDate dataMovimentacao = LocalDate.now();

                        Tela_DashController telaDashController = new Tela_DashController();
                        telaDashController.addFluxoCaixaComparecimento(comparecimento, valorMovimentadoEntrada, dataMovimentacao);

                    }
                }
            }
        } catch (SQLException e) {
            System.out.printf("erro ao inserir no banco: ", e);
        }
        
    }
    
}
