export class Aluno {

  id!: number;
  nome!: string;
  sexo!: string;
  anoNascimento!: number;

  constructor(id: number, nome: string, sexo: string, anoNascimento: number) {
    this.id = id;
    this.nome = nome;
    this.sexo = sexo;
    this.anoNascimento = anoNascimento;
  }
}