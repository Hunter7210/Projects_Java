import com.example.EquipamentoNotFoundException;
import com.example.Connection.MongoConnection;
import com.example.Controllers.EquipamentoController;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipamentoControllerTest {

    @Mock
    private MongoDatabase mockDatabase;

    @Mock
    private MongoCollection<Document> mockCollection;

    @InjectMocks
    private EquipamentoController equipamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(MongoConnection.connectToDatabase()).thenReturn(mockDatabase);
        when(mockDatabase.getCollection("Equipamento")).thenReturn(mockCollection);
    }

    @Test
    void testReadEquipamentoUnico_NullEquipamento_ShouldThrowException() {
        // Arrange
        String codEquip = "E1234";
        when(mockCollection.find(any())).thenReturn(null); // Simulando que não encontrou o equipamento

        // Act & Assert
        EquipamentoNotFoundException exception = assertThrows(EquipamentoNotFoundException.class, () -> {
            equipamentoController.readEquipamentoUnico(codEquip);
        });

        assertEquals("Equipamento com código E1234 não encontrado.", exception.getMessage());
    }

    @Test
    void testReadEquipamentoUnico_EquipamentoFound_ShouldReturnDocument() {
        // Arrange
        String codEquip = "E1234";
        Document expectedDocument = new Document("codEquip", codEquip);
        when(mockCollection.find(any())).thenReturn(expectedDocument);

        // Act
        Document actualDocument = equipamentoController.readEquipamentoUnico(codEquip);

        // Assert
        assertNotNull(actualDocument);
        assertEquals(expectedDocument, actualDocument);
    }
}
