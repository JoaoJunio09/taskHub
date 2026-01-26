# ✅ TaskHub — Gerenciador de Tarefas Premium (Backend Focus)

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=springboot&logoColor=white)
![Backend](https://img.shields.io/badge/Backend--Focused-✔️-blue)
![MVC](https://img.shields.io/badge/Architecture-MVC-informational)
![REST API](https://img.shields.io/badge/API-REST-success)
![Cloud Storage](https://img.shields.io/badge/Cloud-Backblaze%20B2-black)
![Spring Security](https://img.shields.io/badge/Security-Spring%20Security-6DB33F)
![JWT](https://img.shields.io/badge/Auth-JWT-orange)

---

## 📌 Visão Geral

**TaskHub** é um **projeto pessoal e autoral** que desenvolvo com foco total em **backend**, qualidade de software e boas práticas de engenharia.

A ideia central é ser um **gerenciador de tarefas premium**, indo além do básico de “criar, listar e concluir tarefas”, para servir como um **laboratório real de aprendizado**, onde aplico na prática tudo o que estudo sobre **Java, Spring Boot, arquitetura, integração de serviços e escalabilidade**.

Tenho **17 anos** e estou construindo esse projeto pensando como um **produto real**, não apenas como um exercício acadêmico.

---

## 🎯 Propósito do Projeto

Embora muitas funcionalidades possam parecer “além do necessário” para um simples gerenciador de tarefas, todas elas têm um objetivo claro:

> **Aprender na prática como sistemas backend profissionais são projetados, integrados e mantidos.**

Além disso, o TaskHub resolve um **problema real**: ajudar pessoas a se organizarem melhor no dia a dia, oferecendo uma experiência simples, mas com recursos avançados.

---

## ⚙️ Funcionalidades Implementadas

Atualmente, o TaskHub conta com diversas funcionalidades típicas de sistemas profissionais:

### 🧩 Core do Sistema
- ✅ Gerenciamento completo de tarefas
- ✅ Organização e controle por usuário
- ✅ Arquitetura bem definida e separação de responsabilidades

### 🔐 Segurança da Aplicação
- 🔒 Autenticação e autorização com **Spring Security**
- 🔑 Controle de acesso via **Token JWT**
- 👤 Proteção das APIs por perfil e permissões
- 🚫 Endpoints seguros contra acessos não autorizados

### 📧 Comunicação
- 📩 **Envio de e-mails** (notificações e comunicações do sistema)
- 🔔 **Notificações Web Push** para eventos importantes

### 📊 Relatórios
- 📄 **Geração de relatórios em PDF** utilizando **JasperReports**
- 📈 Exportação de dados para análise

### 📂 Arquivos e Armazenamento
- 💾 **Armazenamento local de arquivos**
- ☁️ **Integração com Backblaze B2 (Cloud Storage)**  
  - Upload e gerenciamento de fotos de perfil dos usuários

### 📥 Importação e Exportação de Dados
- 📑 Importação e exportação de dados em **CSV**
- 📊 Importação e exportação de dados em **XLSX (Excel)**

---

## 🔐 Segurança da Aplicação

O TaskHub aplica conceitos reais de **segurança em APIs REST**, utilizando padrões amplamente adotados no mercado:

- Autenticação baseada em **JWT (JSON Web Token)**
- Configuração de segurança com **Spring Security**
- Proteção de rotas sensíveis
- Controle de acesso por autenticação
- Arquitetura preparada para evolução de permissões e perfis

A segurança não foi tratada como um detalhe, mas como parte essencial da arquitetura da aplicação.

## 🧠 O que estou praticando com este projeto

Este repositório é um reflexo direto do meu aprendizado em:

- ✔️ **Java Backend com Spring Boot**
- ✔️ **Segurança de APIs REST com Spring Security e JWT**
- ✔️ **Arquitetura de software**
- ✔️ **Padrão MVC (Model, View, Controller)**
- ✔️ **Separação de responsabilidades**
- ✔️ **Integração com serviços externos**
- ✔️ **Boas práticas de código e organização**
- ✔️ **Escalabilidade, segurança e manutenção**
- ✔️ **Pensamento orientado a produto**

Tudo é desenvolvido com foco em **clareza, organização e evolução contínua**.

---

## 🛠 Arquitetura e Organização

O projeto segue boas práticas de arquitetura, com responsabilidades bem definidas para facilitar:

- Manutenção
- Escalabilidade
- Testabilidade
- Evolução do sistema

```text
📦 taskHub/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── 📂 java/
│   │   │   ├── 📦 controller/     → Camada de controle (REST)
│   │   │   ├── 📦 service/        → Regras de negócio
│   │   │   ├── 📦 repository/     → Persistência de dados
│   │   │   ├── 📦 infrastructure/ → Serviços externos (Gateway)
│   │   │   ├── 📦 model/          → Entidades do sistema
│   │   │   └── 📦 config/         → Configurações gerais
│   │   └── 📂 resources/
│   └── 📂 test/
│       └── 🧪 (em breve) testes automatizados
├── 📜 pom.xml
└── 📄 README.md
```
## 🚧 Funcionalidades Planejadas

O **TaskHub** continuará evoluindo como um produto real.  
Os próximos passos do projeto incluem funcionalidades que ampliam a experiência do usuário e aprofundam meu aprendizado backend:

### 💬 Comunicação em Tempo Real
- Chat em tempo real entre usuários
- Notificações em tempo real integradas ao chat

### 📰 Feed Social de Produtividade
- Publicação de tarefas concluídas
- Curtidas nas publicações
- Comentários
- Interação entre usuários

### 🧪 Qualidade de Software
- Implementação de testes automatizados:
  - Testes unitários
  - Testes de integração

### 🎨 Frontend
- Frontend simples, focado apenas no consumo da API
- Objetivo principal: validar e expor o backend de forma funcional

---

## 🧪 Qualidade e Próximos Passos

Após consolidar meus estudos em **testes automatizados**, o foco será elevar o nível do projeto para um padrão ainda mais profissional:

- Implementar testes unitários e de integração
- Aumentar a confiabilidade e previsibilidade do sistema
- Melhorar a manutenibilidade e segurança do código
- Simular padrões utilizados em **ambientes reais de produção**

O objetivo é tratar o TaskHub como um **software de verdade**, não apenas um projeto de estudo.

---

## 💡 Por que este projeto é importante para mim

O **TaskHub** não é apenas um aplicativo — ele representa:

⭐ Minha evolução contínua como **desenvolvedor backend**  
⭐ Aplicação prática de conceitos avançados de engenharia de software  
⭐ Compromisso com **qualidade, arquitetura e boas práticas**  
⭐ Visão de longo prazo como futuro **engenheiro de software**

Este projeto reflete minha forma de aprender: **criando, errando, melhorando e evoluindo**.

---

## 📫 Contato

Se quiser conversar sobre **Java**, **Spring Boot**, **arquitetura backend**, **projetos pessoais** ou **carreira em desenvolvimento de software**, fique à vontade para entrar em contato 🚀
