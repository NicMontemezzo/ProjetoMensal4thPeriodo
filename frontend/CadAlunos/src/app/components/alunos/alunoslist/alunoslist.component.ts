import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Aluno } from '../../../models/aluno';
import { AlunoService } from '../../../services/aluno.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-alunos-list',
  imports: [RouterLink],
  templateUrl: './alunos-list.component.html',
  styleUrl: './alunos-list.component.scss'
})
export class AlunosListComponent implements OnInit {

  lista: Aluno[] = [];

  private alunoService = inject(AlunoService);

  ngOnInit(): void {
    this.carregarLista();
  }

  carregarLista() {
    this.alunoService.listar().subscribe({
      next: (alunos) => this.lista = alunos,
      error: (err) => {
        console.error(err);
        Swal.fire({
          title: 'Erro ao carregar alunos',
          text: 'Não foi possível conectar ao servidor.',
          icon: 'error',
          confirmButtonText: 'Ok'
        });
      }
    });
  }

  deletar(aluno: Aluno) {
    Swal.fire({
      title: `Excluir ${aluno.nome}?`,
      text: 'Essa ação não pode ser desfeita.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, excluir',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.alunoService.excluir(aluno.id).subscribe({
          next: () => {
            this.carregarLista();
            Swal.fire({ title: 'Aluno excluído!', icon: 'success', confirmButtonText: 'Ok' });
          },
          error: (err) => {
            console.error(err);
            Swal.fire({ title: 'Erro ao excluir', text: 'Tente novamente mais tarde.', icon: 'error', confirmButtonText: 'Ok' });
          }
        });
      }
    });
  }
}