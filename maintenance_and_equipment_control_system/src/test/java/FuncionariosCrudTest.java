import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.example.Connection.MongoConnection;
import com.example.Controllers.FuncionarioController;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class FuncionariosCrudTest {
    private static MongoDatabase database;

    @BeforeAll
    public static void setup() {
        database = MongoConnection.connectToDatabase();
        // Limpa a coleção antes dos testes
        database.getCollection("Funcionarios").drop();
    }

    @AfterEach
    public void tearDown() {
        database.getCollection("Funcionarios").drop(); // Limpa a coleção após cada teste
    }

    @Test
    public void testCreateFuncionario() {
        // Criar um novo funcionário
        FuncionarioController.createFuncionario("João Silva", "12345", "Manutenção", "Técnico", "555-1234", "joao@empresa.com", "senhaSegura");

        // Verifica se o funcionário foi criado
        MongoCollection<Document> collection = database.getCollection("Funcionarios");
        Document found = collection.find(new Document("nomeFunc", "João Silva")).first();
        assertNotNull(found, "Funcionário não foi encontrado após a criação.");
    }

    @Test
    public void testReadFuncionario() {
        // Criar e ler um funcionário
        FuncionarioController.createFuncionario("João Silva", "12345", "Manutenção", "Técnico", "555-1234", "joao@empresa.com", "senhaSegura");

        // Ler o funcionário
        Document found = database.getCollection("Funcionarios").find(new Document("nomeFunc", "João Silva")).first();
        assertNotNull(found, "Funcionário não foi encontrado.");
    }

    @Test
    public void testDeleteFuncionario() {
        // Criar um novo funcionário
        FuncionarioController.createFuncionario("João Silva", "12345", "Manutenção", "Técnico", "555-1234", "joao@empresa.com", "senhaSegura");

/*         // Deletar o funcionário
        FuncionarioController.deleteFuncionario("João Silva");
 */
        // Verifica se o funcionário foi deletado
        Document found = database.getCollection("Funcionarios").find(new Document("nomeFunc", "João Silva")).first();
        assertNull(found, "Funcionário ainda existe após a exclusão.");
    }
}
