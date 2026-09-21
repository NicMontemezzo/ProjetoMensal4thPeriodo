import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Aluno } from '../../../models/aluno';
import { AlunoService } from '../../../services/aluno.service';
import { MdbModalModule, MdbModalRef } from 'mdb-angular-ui-kit/modal';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-alunos-list',
  imports: [RouterLink, MdbModalModule],
  templateUrl: './alunos-list.component.html',
  styleUrl: './alunos-list.component.scss'
})
export class AlunosListComponent implements OnInit {

  lista: Aluno[] = [];
  alunoSelecionado: Aluno | null = null;

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

  selecionarParaExcluir(aluno: Aluno, modal: MdbModalRef) {
    this.alunoSelecionado = aluno;
    modal.show();
  }

  confirmarExclusao(modal: MdbModalRef) {
    if (!this.alunoSelecionado) return;

    this.alunoService.excluir(this.alunoSelecionado.id).subscribe({
      next: () => {
        modal.hide();
        this.carregarLista();
        Swal.fire({ title: 'Aluno excluído!', icon: 'success', confirmButtonText: 'Ok' });
      },
      error: (err) => {
        console.error(err);
        modal.hide();
        Swal.fire({ title: 'Erro ao excluir', text: 'Tente novamente mais tarde.', icon: 'error', confirmButtonText: 'Ok' });
      }
    });
  }
}