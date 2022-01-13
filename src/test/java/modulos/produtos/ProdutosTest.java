package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

@DisplayName("Teste Web do módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){

        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar tamanho da janela
        this.navegador.manage().window().maximize();

        // Navegar até a página da lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }


    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        // Fazer login
        // Acesso ao Formulário adicição de novo Produto
        // Cadastrar um novo Produto
        // Com Design Patterns
        String messageApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Desktop Zao")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", messageApresentada);
        // Vou submeter o formulário
        // Vou validar que a mensagem de erro foi apresentado
        // <script>M.toast({html: 'O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00', classes: 'rounded'})</script>
    }

    @Test
    @DisplayName ("Testes para validar mensagem para produtos com valores maiores que 7mil")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){


        // Fazer login
        // Acesso ao Formulário adicição de novo Produto
        // Cadastrar um novo Produto
        // Com Design Patterns
        String messageApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Desktop Zao")
                .informarValorDoProduto("8000001")
                .informarCoresDoProduto("Preto, Branco")
                .submeterFormularioComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", messageApresentada);
        // Vou submeter o formulário
        // Vou validar que a mensagem de erro foi apresentado
        // <script>M.toast({html: 'O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00', classes: 'rounded'})</script>

    }
    @Test
    @DisplayName("Posso cadastrar produtos que estejam na faixa de 0,01 à 7.000,00")
    public void testPossoCadastrarProdutoComValorDeUmCentavoASeteMilReais(){
       String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MACBUK")
                .informarValorDoProduto("30000")
                .informarCoresDoProduto("Azulinho, Branquinho")
                .submeterFormularioDeEdicaoComSucesso()
               .capturarMensagemApresentada();

       Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }
    @Test
    @DisplayName("Vou incluir um produto com valor 7.000,00")
    public void testIncluirProdutoComValorSeteMil(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("MACKINTOSHI")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("Preto, Branquinho")
                .submeterFormularioDeEdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    // Vou fechar o navegador
    @AfterEach
    public void afterEach() {
    navegador.quit();
    }

}
