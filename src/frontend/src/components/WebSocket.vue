<template>
<p>Bitcoin Price: {{USDollarFormat.format(BTCUSD)}}</p>
<p>Etherium Price: {{USDollarFormat.format(ETHUSD)}}</p>
</template>

<script>
const wsURL = 'wss://ws-feed.exchange.coinbase.com';

export default {
    name: 'WebSocket',
    data(){
        return {
          ETHUSD: '',
          BTCUSD: '',
          socket: null,
          subscription: {
            type: "subscribe",
            product_ids: [
               "ETH-USD",
               "BTC-USD"
            ],
            channels: [
               {
                   name: "ticker",
                   product_ids: [
                       "BTC-USD",
                       "ETH-USD"
                   ]
               }
            ]
          },
          USDollarFormat: new Intl.NumberFormat('en-US', {
              style: 'currency',
              currency: 'USD',
          }),
        }
    },
    methods: {
        createWebSocketConnection(){
            this.socket = new WebSocket(wsURL);

            this.socket.onopen = () => {
              this.socket.send(JSON.stringify(this.subscription));
              console.log("WebSocket connection opened:");
            }

            this.socket.onmessage = (msg) => {
              this.parseTicker(JSON.parse(msg?.data))
            };

            this.socket.onerror = (err) => {
              console.log(`WebSocket error: ${err}`);
            };

            this. socket.onclose = (event) => {
              console.log(`WebSocket connection closed: ${event.code}`);
            };
        },
        parseTicker(ticker){
            if(ticker?.product_id === 'ETH-USD'){
                this.ETHUSD = ticker?.price;
            }
            if(ticker?.product_id === 'BTC-USD'){
                this.BTCUSD = ticker?.price;
            }
        }
    },
    created(){
        this.createWebSocketConnection();
    }
}
</script>

<style scoped>

</style>