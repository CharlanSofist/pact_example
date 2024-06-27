# pact_example

1 - Na pasta root do projeto execute o comando 'docker build -t jenkins-docker .'

2 - Suba os container com o comando docker-compose up -d.

3 - Configure o Jenkins.

3.1 - Configure o usuario e senha sendo 'jenkins'.

3.2 - Instale os plugins:
        Copy Artifact Plugin
        Docker Pipeline

4 - Crie os jobs na ordem abaixo:

- > Build_pipeline
    - > executors - > Can_I_Deploy
    - > executors - > Consumer_Tests
    - > executors - > Provider_Tests
    - > executors - > Publish_Contracts.

5 - Para rodar a esteira basta executar o build 'Build_pipeline'.