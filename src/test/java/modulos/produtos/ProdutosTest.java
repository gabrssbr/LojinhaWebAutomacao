package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Teste Web do módulo de Produtos")
public class ProdutosTest {

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){

        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Maximizar tamanho da janela
        navegador.manage().window().maximize();

        // Navegar até a página da lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Ir até a tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Preencher dados do produto e o valor será igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Desktop Top");
        navegador.findElement(By.id("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("Preto, Branco");

        // Vou submeter o formulário
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentado
        // <script>M.toast({html: 'O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00', classes: 'rounded'})</script>
        String messageToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", messageToast);


    }
}
