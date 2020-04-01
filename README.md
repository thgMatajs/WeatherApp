### Projeto executado: App que mostra o clima conforme a localização do dispositivo ###

>IDE utilizada: Android Studio 3.1.3  
>Linguagem: JAVA  
>Libs: Retrofit, GSON  
>SDK min: 19  
>API: Yahoo Weather  

### Maneiras de executar o App:

1. Baixe o apk clicando [**AQUI**](https://drive.google.com/open?id=1eZeQFOH5KzUTe910BQvQW1OVzpwDiMkz), e instale em um dispositivo Android.

2. Baixe o projeto compactado clicando [**AQUI**](https://drive.google.com/open?id=1wwNRlrSGFlEgR_A1U3cKYvH8zztrx0J6) e descompacte em seu computador, será necessário ter o Android Studio instalado, de preferencia o 3.1.3.
Após descompactar, inicie o Android Studio e abra o projeto, em seguida mande executar em um dispositivo físico ou em um emulador.

3. Faça um clone do projeto no GitHub utilizando este link: *https://github.com/thgcode0101/TruckPadWeather.git*  
Será necessário ter o Android Studio instalado, de preferencia o 3.1.3.
Abra o Android Studio vai em **VCS > Checkout from Version Control > GitHub**, cole o link em **Git Repository URL** e clique em **Clone**
em seguida mande executar em um dispositivo físico ou em um emulador.


### Como o App funciona:

1. Ao iniciar o App a primeira vez vem uma solicitação de permissão para que o app tenha acesso a localização do dispositivo.

2. Após isso criei um método para verificar se o GPS está ativado, caso não esteja, através de uma *Intent* é feita uma  chamada implícita de uma tela para que seja   ativado o GPS.

3. Com o GPS ativado é chamado um metodo que pega a posição do dispositivo utilizando a classe *LocationListener*. 

4. Com a *Latitude* e *Longitude* do dispositivo são passadas como parâmetro para um método que é responsável por fazer a chamada a API do YahooWeather, a comunicação é feita utilizando o Retrofit.  
O resultado desta chamada são JSON e são armazanados em suas respectivas Classes. Criei uma outra Classe *WeatherResult* com apenas as informações que quero desta resposta como atributos. Passei a objeto *WeatherResult* via *putExtra* para a proxima Activity *WeatherActivity*.

5. A Activity *WeatherActivity* recebe o objeto e assim apresenta as informações obtidas na resposta da comunicação com a API.




