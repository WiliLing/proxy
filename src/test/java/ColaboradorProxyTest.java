import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class ColaboradorProxyTest {

    @BeforeEach
    void setUp() {
        BD.addColaborador(new Colaborador(1, "uiu", "Juiz de Fora", "achieved"));
        BD.addColaborador(new Colaborador(2, "rodrigo", "Juiz de Fora", "partially achieved"));
        BD.addColaborador(new Colaborador(3, "Lucas", "Juiz de Fora", "exceeded"));
    }

    @Test
    void deveRetornarDadosPessoaisColaborador() {
        ColaboradorProxy colaborador = new ColaboradorProxy(1);

        assertEquals(Arrays.asList("uiu", "Juiz de Fora"), colaborador.obterDadosPessoais());
    }

    @Test
    void deveRetonarPerformanceColaborador() {
        Funcionario funcionario = new Funcionario("Igor", true);
        ColaboradorProxy colaborador = new ColaboradorProxy(2);

        assertEquals(Arrays.asList("partially achieved"), colaborador.obterPerformance(funcionario));
    }

    @Test
    void deveRetonarDadosPessoaisEPerformanceColaborador() {
        Funcionario funcionario = new Funcionario("Diego", true);
        ColaboradorProxy colaborador = new ColaboradorProxy(3);

        assertEquals(Arrays.asList("Lucas", "Juiz de Fora"), colaborador.obterDadosPessoais());
        assertEquals(Arrays.asList("exceeded"), colaborador.obterPerformance(funcionario));
    }

    @Test
    void deveRetonarExcecaoUsuarioNaoAutorizadoConsultarPerformanceColaborador() {
        try {
            Funcionario funcionario = new Funcionario("Thiago", false);
            ColaboradorProxy colaborador = new ColaboradorProxy(2);

            colaborador.obterPerformance(funcionario);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Funcionário não autorizado", e.getMessage());
        }
    }
}
