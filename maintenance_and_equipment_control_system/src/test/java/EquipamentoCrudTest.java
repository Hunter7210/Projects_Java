import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import com.example.Connection.MongoConnection;
import com.example.Controllers.EquipamentoControler;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Collections;

public class EquipamentoCrudTest {
    private static MongoDatabase database;

    @BeforeAll
    public static void setup() {
        database = MongoConnection.connectToDatabase();
        // Limpa a coleção antes dos testes
        database.getCollection("Equipamento").drop();
    }

    @AfterEach
    public void tearDown() {
        database.getCollection("Equipamento").drop(); // Limpa a coleção após cada teste
    }

    @Test
    public void testCreateEquipamento() {
        EquipamentoControler eq = new EquipamentoControler();
        // Criar um novo equipamento
        eq.createEquipamento(
                "Máquina Teste",
                "2023-01-01",
                "Máquina Industrial",
                "Fornecedor XYZ",
                5,
                "Operacional",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        // Verifica se o equipamento foi criado
        MongoCollection<Document> collection = database.getCollection("Equipamento");
        Document found = collection.find(new Document("nomeEqui", "Máquina Teste")).first();
        assertNotNull(found, "Equipamento não foi encontrado após a criação.");
    }

    @Test
    public void testReadEquipamento() {
        // Criar e ler um equipamento
        EquipamentoControler.createEquipamento(
                "Máquina Teste",
                "2023-01-01",
                "Máquina Industrial",
                "Fornecedor XYZ",
                5,
                "Operacional",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        // Ler o equipamento
        EquipamentoControler.readEquipamento("Máquina Teste");
        Document found = database.getCollection("Equipamento").find(new Document("nomeEqui", "Máquina Teste")).first();
        assertNotNull(found, "Equipamento não foi encontrado.");
    }

    @Test
    public void testUpdateEquipamento() {
        // Criar um novo equipamento
        EquipamentoControler.createEquipamento(
                "Máquina Teste",
                "2023-01-01",
                "Máquina Industrial",
                "Fornecedor XYZ",
                5,
                "Operacional",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        // Atualizar o status do equipamento
        EquipamentoControler.updateEquipamento("Máquina Teste", "Manutenção");

        // Verificar se o status foi atualizado
        Document found = database.getCollection("Equipamento").find(new Document("nomeEqui", "Máquina Teste")).first();
        assertEquals("Manutenção", found.getString("statusEquip"), "O status do equipamento não foi atualizado corretamente.");
    }

    @Test
    public void testDeleteEquipamento() {
        // Criar um novo equipamento
        EquipamentoControler.createEquipamento(
                "Máquina Teste",
                "2023-01-01",
                "Máquina Industrial",
                "Fornecedor XYZ",
                5,
                "Operacional",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        // Deletar o equipamento
        EquipamentoControler.deleteEquipamento("Máquina Teste");

        // Verifica se o equipamento foi deletado
        Document found = database.getCollection("Equipamento").find(new Document("nomeEqui", "Máquina Teste")).first();
        assertNull(found, "Equipamento ainda existe após a exclusão.");
    }
}
