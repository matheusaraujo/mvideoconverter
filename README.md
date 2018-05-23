# mvideoconverter

**mvideoconverter** - conversor online de vídeo para formato web

Rodando em: https://young-spire-26394.herokuapp.com/mvideoconverter

## Documentação

### Diagrama de componentes

![Componentes](/docs/componentes.jpg)

### Diagrama de sequência

![Sequência](/docs/sequencia.png)

#### Notas
* Classe Encoding 
	* Utiliza Zencoder
	* Envio de arquivos
		* Envia apenas um arquivo
		* Converte para apenas um formato, padrão **MP4**
		* Sempre armazena o arquivo convertido no Amazon S3