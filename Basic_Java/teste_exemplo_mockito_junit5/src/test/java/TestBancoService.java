import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import com.example.BancoService;
import com.example.ContaBancaria;
import com.example.ContaBancariaRepository;


public class TestBancoService {
    private ContaBancariaRepository repositoryMock;
    private BancoService bancoService;
    private ContaBancaria contaMock;


    @BeforeEach
    public void setUp() {
        // Criar mock do repositório
        repositoryMock = Mockito.mock(ContaBancariaRepository.class);


        // Criar instância do serviço com o mock injetado
        bancoService = new BancoService(repositoryMock);


        // Criar mock de uma conta bancária para usar nos testes
        contaMock = new ContaBancaria("12345", 1000.0);
    }


    @Test
    public void testDepositarComSucesso() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);


        // Executar o método de depósito
        bancoService.depositar("12345", 500.0);


        // Verificar se o saldo foi atualizado corretamente
        assertEquals(1500.0, contaMock.getSaldo(),0);


        // Verificar se o método de atualizar foi chamado no repositório
        verify(repositoryMock).atualizar(contaMock);
    }


    @Test
    public void testSacarComSucesso() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);


        // Executar o método de saque
        bancoService.sacar("12345", 300.0);


        // Verificar se o saldo foi atualizado corretamente
        assertEquals(700.0, contaMock.getSaldo(),0);


        // Verificar se o método de atualizar foi chamado no repositório
        verify(repositoryMock).atualizar(contaMock);
    }


     @Test
    public void testSacarSaldoInsuficiente() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);


        // Verificar se a exceção é lançada ao tentar sacar mais que o saldo disponível
        assertThrows(IllegalArgumentException.class, () -> {
            bancoService.sacar("12345", 2000.0);
        });


        // Verificar que o saldo não foi alterado
        assertEquals(1000.0, contaMock.getSaldo(),0);


        // Verificar que o método de atualizar NÃO foi chamado, pois a operação falhou
        verify(repositoryMock, never()).atualizar(contaMock);
    }


    @Test
    public void testDepositarValorNegativo() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);


        // Verificar se a exceção é lançada ao tentar depositar valor negativo
        assertThrows(IllegalArgumentException.class, () -> {
            bancoService.depositar("12345", -100.0);
        });


        // Verificar que o saldo não foi alterado
        assertEquals(1000.0, contaMock.getSaldo(),0);


        // Verificar que o método de atualizar NÃO foi chamado, pois a operação falhou
        verify(repositoryMock, never()).atualizar(contaMock);
    }


    @Test
    public void testConsultarSaldoComSucesso() {
        // Simular comportamento do repositório
        when(repositoryMock.encontrarPorNumero("12345")).thenReturn(contaMock);


        // Executar o método de consulta de saldo
        double saldo = bancoService.consultarSaldo("12345");


        // Verificar se o saldo retornado é o correto
        assertEquals(1000.0, saldo,0);


        // Verificar se o método de encontrarPorNumero foi chamado no repositório
        verify(repositoryMock).encontrarPorNumero("12345");
    }


    @Test
    public void testContaNaoEncontrada() {
        // Simular comportamento do repositório: conta não existe
        when(repositoryMock.encontrarPorNumero("99999")).thenReturn(null);


        // Verificar se a exceção é lançada ao tentar realizar operações em conta inexistente
        assertThrows(IllegalArgumentException.class, () -> {
            bancoService.consultarSaldo("99999");
        });


        // Verificar que o método de atualizar NUNCA foi chamado
        verify(repositoryMock, never()).atualizar(any());
    }
 
}

