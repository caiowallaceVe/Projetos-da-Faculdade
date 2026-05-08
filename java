let carrinhoItens = [];
let totalDinheiro = 0;

function abrirAba(nomeAba) {
    const abas = document.querySelectorAll(".tab-content");
    abas.forEach(aba => aba.classList.remove("active-tab"));
    document.getElementById(nomeAba).classList.add("active-tab");
}

function adicionarAoCarrinho(idSelect) {
    const select = document.getElementById(idSelect);
    const valorTexto = select.value;
    const preco = parseInt(valorTexto.split('R$')[1]);

    carrinhoItens.push(valorTexto);
    totalDinheiro += preco;

    atualizarCarrinho();
    alert("Item adicionado ao carrinho!");
}

function atualizarCarrinho() {
    const lista = document.getElementById("lista-carrinho");
    document.getElementById("contagem").innerText = carrinhoItens.length;
    document.getElementById("valor-total").innerText = totalDinheiro;

    if (carrinhoItens.length === 0) {
        lista.innerHTML = '<p style="color: #999;">O carrinho está vazio...</p>';
        return;
    }

    lista.innerHTML = "";
    carrinhoItens.forEach(item => {
        const div = document.createElement("div");
        div.className = "item-carrinho";
        div.innerHTML = `<span>✅ ${item}</span>`;
        lista.appendChild(div);
    });
}

function limparCarrinho() {
    if(confirm("Deseja limpar todo o carrinho?")) {
        carrinhoItens = [];
        totalDinheiro = 0;
        atualizarCarrinho();
    }
}

function enviarWhatsapp() {
    if (carrinhoItens.length === 0) {
        alert("Seu carrinho está vazio!");
        return;
    }

    let mensagem = "☕ *Novo Pedido - Grão & Aroma*%0A%0A";
    carrinhoItens.forEach((item, index) => {
        mensagem += `${index + 1}. ${item}%0A`;
    });
    mensagem += `%0A💰 *Total: R$ ${totalDinheiro},00*`;

    const numero = "5519994995101";
    const url = `https://wa.me/${numero}?text=${mensagem}`;

    window.open(url, '_blank');
}
