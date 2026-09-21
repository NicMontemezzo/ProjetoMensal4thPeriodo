import { Component, OnInit, inject } from '@angular/core';
import { Produto } from '../../../models/produto';
import { ProdutoService } from '../../../services/produto.service';

@Component({
  selector: 'app-produtos-list',
  imports: [],
  templateUrl: './produtos-list.component.html',
  styleUrl: './produtos-list.component.scss'
})
export class ProdutosListComponent implements OnInit {

  lista: Produto[] = [];

  private produtoService = inject(ProdutoService);

  ngOnInit(): void {
    this.produtoService.listar().subscribe({
      next: (produtos) => this.lista = produtos,
      error: (err) => console.error('Erro ao carregar produtos', err)
    });
  }
}