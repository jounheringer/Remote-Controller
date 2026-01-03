# Android IR Remote Controller (Samsung TV)

## Propósito

Este projeto tem como objetivo **testar e validar o uso do emissor infravermelho (IR Blaster) de dispositivos Android**, criando um **controle remoto funcional** para uma **Smart TV Samsung**.

O foco principal não é substituir controles comerciais, mas **explorar a integração direta com hardware IR no Android**, entendendo protocolos, timings e limitações reais da plataforma.

---

## Objetivo do MVP

* Verificar se o dispositivo Android possui emissor IR
* Emitir sinais infravermelhos via `ConsumerIrManager`
* Enviar comandos básicos (ex: Power, navegação, OK)
* Controlar uma TV Samsung utilizando IR

---

## Arquitetura

O projeto utiliza a arquitetura **MVP (Model–View–Presenter)**, com separação clara de responsabilidades:

```
View (Compose UI)
   ↓
Presenter
   ↓
Infra (IR Transmitter / Protocol Encoder)
```

### Responsabilidades

* **View**

  * UI em Jetpack Compose
  * Encaminha eventos do usuário (cliques, direções, OK)

* **Presenter**

  * Contém a lógica de interação
  * Decide qual comando IR enviar
  * Não depende de Android UI diretamente

* **Infra / Data**

  * Integração com `ConsumerIrManager`
  * Implementação do protocolo IR (Samsung / NEC-like)

Essa abordagem facilita testes, manutenção e entendimento do fluxo do app.

---

## Stack Tecnológica

* **Kotlin**
* **Jetpack Compose**
* **Android SDK**
* **ConsumerIrManager (IR Blaster API)**
* **Arquitetura MVP**

---

## Interface

O app implementa um **D-Pad customizado**, inspirado em controles físicos:

* Direções: ↑ ↓ ← →
* Botão central (OK / Select)
* Interação baseada em áreas clicáveis (Canvas + gestos)
* Botões genericos de aumentar ou diminuir 

A UI foi pensada para simular a experiência real de um controle remoto.

---

## Uso do Infravermelho no Android

* Emissão de IR via `ConsumerIrManager.transmit()`
* Frequência padrão: **38 kHz**
* Geração de sinais baseada em:

  * Timings raw (`on/off`)
  * Protocolo Samsung (baseado em NEC)
* Conversão de códigos hexadecimais para padrões compatíveis com Android

---

## Status do Projeto

**Projeto pausado**

### Motivo

Apesar da implementação correta da arquitetura, UI e transmissão de IR:

* Não foi possível encontrar **códigos IR específicos** para o modelo da TV Samsung testada
* Apenas **códigos genéricos de Samsung** foram utilizados
* Esses códigos **não funcionaram corretamente** no modelo específico da TV

Como muitos modelos modernos da Samsung utilizam:

* Combinação de **IR + Bluetooth**
* Protocolos proprietários
* Códigos não documentados publicamente

o projeto foi pausado até que seja possível:

* Capturar os códigos reais com hardware externo (ex: receptor IR, Arduino, Flipper Zero)
* Ou testar em outro modelo de TV compatível com códigos genéricos

---

## Aprendizados

Mesmo com o projeto pausado, os principais aprendizados incluem:

* Funcionamento de protocolos IR (Samsung / NEC)
* Geração de sinais infravermelhos em baixo nível
* Limitações reais de hardware e fabricantes
* Implementação de UI customizada em Compose
* Arquitetura MVP aplicada a hardware

---

## Possíveis Evoluções Futuras

* Captura de códigos IR via hardware externo (Possivel projeto em um vArduino)
* Persistência de dispositivos configurados
* Documentação detalhada dos protocolos IR

---

## Observação Final

Este projeto tem caráter **experimental e educacional**, focado em aprendizado profundo de plataforma Android, hardware e arquitetura — não em produção comercial.
