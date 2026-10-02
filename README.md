# Relatório: Sistema de Controle de Aquisições
**Trabalho 1 – Prática com Git em Times**  
**Disciplina:** Gerenciamento de Configuração de Software  
**Professor:** Michael da Costa Móra

---

## 1. Identificação do Time (Folha de Rosto)
**Componentes do Time:**
- **Lucas Goettert Lopes** | GitHub: [@LucasGL1](https://github.com/LucasGL1) *(Membro 5)*
- **Francisco Jung** | GitHub: [@FranciscoJung](https://github.com/FranciscoJung)
- **Natan Stallivieri** | GitHub: [@NatanStallivieri587](https://github.com/NatanStallivieri587)
- **Lucas Oliveira Porto** | GitHub: [@LucasPQoliveira](https://github.com/LucasPQoliveira)
- **Matheus Agostini** | GitHub: [@MatheusAgostini587](https://github.com/MatheusAgostini587)

> **Aviso de Abandono:**
> O planejamento original deste trabalho foi idealizado para 6 pessoas, com uma divisão estrita de 12 tarefas (2 para cada). Contudo, o colega encarregado das tarefas do "Membro 2" (Feature 2A: Carga de Dados Iniciais e Feature 2B: Avaliação de Pedidos pelo Administrador) abandonou a disciplina/curso durante a execução do projeto. Como forma de manter o software funcional e cumprir com os requisitos globais de entrega, a equipe remanescente absorveu e codificou de última hora as funcionalidades essenciais ausentes em uma branch de correções.

## 2. Link para o Repositório Público
[https://github.com/NatanStallivieri587/SistemaControleDeAquisicoes](https://github.com/NatanStallivieri587/SistemaControleDeAquisicoes)

## 3. Fluxo de Trabalho Adotado (Branching Model)
A equipe adotou uma abordagem baseada no **Git Flow** adaptado para as necessidades do projeto. A escolha desse modelo se justificou pela necessidade de trabalhar simultaneamente em diferentes partes do sistema (Motor do Console, Validações, Mocks, Buscas e Filtros) sem que o código possivelmente incompleto de um membro quebrasse a aplicação para os demais que estavam desenvolvendo funcionalidades vitais do domínio em paralelo.

**Regras do Fluxo Adotado e Seguido:**
- **`main`**: Branch principal que contém apenas código de produção estável, finalizado e sem erros de compilação.
- **`develop`**: Branch de integração diária. Todo o código funcional testado das features é reunido e mesclado nesta ramificação antes de ir para a `main`.
- **`feature/*`**: Cada membro criou branches exclusivas a partir da `develop` para desenvolver suas respectivas funcionalidades (ex: `feature/5a-validacao-limites`, `feature/1b-excluir-pedido`, `feature/3b-busca-datas`, etc). Ao finalizar a tarefa, a branch era mesclada (`merge`) de volta para a `develop`.

## 4. Demonstração do Fluxo de Trabalho
> **⚠️ Atenção Integrantes:** *[Inserir aqui as capturas de tela do "Network graph" do GitHub (aba Insights > Network) e capturas de tela da lista de commits provando que as branches foram criadas e a participação de todos ocorreu.]*

A equipe seguiu estritamente o fluxo planejado. Cada membro atuou dentro de sua respectiva branch isolada. O histórico de commits demonstra múltiplos fluxos se separando da `develop` e posteriormente sendo reintegrados de forma assíncrona através de *Merges*. Os logs gerados pelo comando `git shortlog -s -n --all --no-merges` comprovam o atingimento da cota de contribuição substancial requerida para cada aluno ativo.

## 5. Conclusão e Lições Aprendidas
Durante o desenvolvimento do Sistema de Controle de Aquisições, enfrentamos diversos desafios reais inerentes ao trabalho colaborativo com repositórios distribuídos. Tais obstáculos enriqueceram significativamente o nosso aprendizado prático:

1. **Desafios de Integração (Merge Conflicts):** Como o sistema em modo console centraliza grande parte da sua lógica e de seus menus iterativos (`switch/case`) em apenas um arquivo (`Main.java`), sofremos com sucessivos conflitos de *merge* quando múltiplos colegas tentavam injetar suas novas opções de menu nas mesmas linhas simultaneamente. O grupo aprendeu na prática a importância de ler as *diffs*, utilizar ferramentas de resolução de conflitos, isolar lógicas sem sobrescrever o trabalho alheio e mesclar alterações mantendo opções integradas.
2. **Reversões Perigosas e Recuperação de Código:** O maior incidente do projeto ocorreu quando um *Pull Request* de *Revert* foi aprovado e misturado de forma equivocada diretamente na branch `main`, deletando acidentalmente quatro classes essenciais do modelo de domínio do sistema (`ItemPedido.java`, `Produto.java`, etc.). A compilação geral quebrou. A equipe superou o problema utilizando ferramentas do próprio Git para resgatar arquivos específicos; realizamos o `checkout` dessas classes deletadas buscando suas versões perfeitas que ainda residiam na branch `develop`, restaurando-as e salvando a entrega no último minuto sem danos irreversíveis.
3. **Erros de Compilação por Falta de Teste Local:** Tivemos que lidar com incidentes onde ocorreu a inserção de loops infinitos sem tratamento na `develop` e erros simples de nomenclatura (arquivos Java nomeados de forma incompatível com a classe pública interna). Isso gerou quebras no build de todos os desenvolvedores após o `git pull` e ensinou ao time uma das regras de ouro mais severas de trabalho em equipe: a urgência de sempre compilar o projeto localmente (`javac`) e atestar seu funcionamento antes de realizar um `push`.
4. **Abandono e Resiliência do Time:** A ausência drástica do membro 2 poderia ter causado a inatividade do software por falta de cadastros iniciais e ferramentas cruciais de bloqueio do sistema. Desenvolvemos resiliência criando e integrando uma branch derradeira de `correções-finais` para injetar permissões ocultas, mocks alternativos e salvar as interfaces, provando que um fluxo de repositório descentralizado bem estruturado permite a manobra de recuperação das partes soltas facilmente.

Em suma, o trabalho atestou na prática que o Git vai muito além do mero armazenamento em nuvem de "versões" de código. Ele provou ser uma ferramenta vital de gestão de crises e governança de times que, quando aliado a um fluxo bem definido, permite que dezenas de códigos concorrentes — e até mesmo incidentes destrutivos — sejam estabilizados e revertidos em minutos sem o sacrifício do trabalho coletivo.
