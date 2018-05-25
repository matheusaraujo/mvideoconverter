# mvideoconverter

**mvideoconverter** - Conversor online de vídeo para formato Web.

Live Demo: https://young-spire-26394.herokuapp.com/

## Visão Geral
A aplicação nesse repositório é um conversor online de vídeos. Ela recebe vídeos em qualquer formato e converte para o formato MP4, compatível com a Web.

Os arquivos são armazenados na [Amazon S3](https://aws.amazon.com/s3/) e convertidos pela ferramenta [Zencoder](https://zencoder.com/). 

A aplicação Demo está rodando no [Heroku](http://www.heroku.com).

*Por limitações da versão free da ferramenta Zencoder, os vídeos convertidos têm, no máximo, 5 segundos.*

## Diagramas

### Diagrama de componentes
A aplicação está dividida em um WebApp AngularJS e uma API em Java Spring, além dos Amazon S3 e Zencoder.

![Componentes](/docs/componentes.jpg)


### Diagrama de sequência
A conversão e exibição do vídeo se dá nos seguintes passos:

1. Usuário seleciona arquivo
1. Angular App requisita uma URL Pre-Signed para a API
1. API requisita a URL Pre-Signed para o Amazon S3
1. Angular App sobe o arquivo para a Amazon S3
1. Angular App notifica a API sobre o upload
1. API cria um Job no Zencoder e retorna para o App
1. Angular App requisita o status do vídeo até que esteja convertido
1. O vídeo está convertido
1. Angular App exibe o vídeo

![Sequência](/docs/sequencia.png)

## Próximos passos
Algumas sugestão de melhorias são:
* Funcionalidades
	* Possibilitar escolher formato da conversão
	* Possibilitar converter arquivo a partir de uma URL
	* Armazenar uma referência para os vídeos convertidos, de forma que eles possam ser apenas exibidos em um segundo acesso	
* Alterações em código
	* Refatorar a API usando injeção de dependências
	* Criar arquivos de configuração externos ao código
	
Acesse o [Projeto](https://github.com/matheusaraujo/mvideoconverter/projects/1) para acompanhar.

## Referências
* Heroku
	* https://devcenter.heroku.com/articles/getting-started-with-java
	* https://github.com/heroku/java-getting-started
	* https://devcenter.heroku.com/articles/s3
* Zencoder
	* https://zencoder.com/
	* https://github.com/zencoder/zencoder-java
* Amazon S3
	* https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/getting-started.html
	* https://docs.aws.amazon.com/AmazonS3/latest/dev/ShareObjectPreSignedURLJavaSDK.html
	* https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
	* https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html
	* https://havecamerawilltravel.com/photographer/how-allow-public-access-amazon-bucket/
* AngularJS + S3
	* http://www.cheynewallace.com/uploading-to-s3-with-angularjs-and-pre-signed-urls/
	* https://github.com/cheynewallace/angular-s3-upload